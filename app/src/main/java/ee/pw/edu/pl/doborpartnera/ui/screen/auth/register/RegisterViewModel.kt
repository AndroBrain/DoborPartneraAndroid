package ee.pw.edu.pl.doborpartnera.ui.screen.auth.register

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.core.result.getMessage
import ee.pw.edu.pl.doborpartnera.core.validation.BirthdateValidator
import ee.pw.edu.pl.doborpartnera.core.validation.EmailValidator
import ee.pw.edu.pl.doborpartnera.core.validation.GenderValidation
import ee.pw.edu.pl.doborpartnera.core.validation.NameLengthValidator
import ee.pw.edu.pl.doborpartnera.core.validation.PasswordValidator
import ee.pw.edu.pl.doborpartnera.core.validation.RepeatPasswordValidator
import ee.pw.edu.pl.doborpartnera.core.validation.Validator
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.domain.core.result.fold
import ee.pw.edu.pl.domain.usecase.auth.register.RegisterForm
import ee.pw.edu.pl.domain.usecase.auth.register.RegisterUseCase
import ee.pw.edu.pl.domain.usecase.profile.Gender
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<RegisterState>(savedStateHandle, RegisterState()) {

    fun changeEmail(email: String) {
        updateState { state -> state.copy(email = email, emailError = null) }
    }

    fun changeName(name: String) {
        updateState { state -> state.copy(name = name, nameError = null) }
    }

    fun changeSurname(surname: String) {
        updateState { state -> state.copy(surname = surname, surnameError = null) }
    }

    fun changePassword(password: String) {
        updateState { state -> state.copy(password = password, passwordError = null) }
    }

    fun changeRepeatPassword(repeatPassword: String) {
        updateState { state ->
            state.copy(
                repeatPassword = repeatPassword,
                repeatPasswordError = null
            )
        }
    }

    fun changeBirthdate(timeStamp: Long) {
        updateState { state -> state.copy(birthdate = timeStamp, birthdateError = null) }
    }

    fun changeGender(gender: Gender) {
        updateState { state -> state.copy(gender = gender, genderError = null) }
    }

    fun clearErrorMsg() {
        updateState { state -> state.copy(errorMsg = null) }
    }

    fun registered() {
        updateState { state -> state.copy(isRegistered = false) }
    }

    fun register() {
        viewModelScope.launch {
            updateState { state -> state.copy(isLoading = true) }
            val currentState = state.value
            val emailError = Validator.validate(currentState.email, EmailValidator)
            if (emailError != null) {
                updateState { state -> state.copy(emailError = emailError) }
            }
            val nameError = Validator.validate(
                currentState.name,
                NameLengthValidator(R.string.validation_err_name_length)
            )
            if (nameError != null) {
                updateState { state -> state.copy(nameError = nameError) }
            }
            val surnameError = Validator.validate(
                currentState.surname,
                NameLengthValidator(R.string.validation_err_surname_length)
            )
            if (surnameError != null) {
                updateState { state -> state.copy(surnameError = surnameError) }
            }
            val passwordError = Validator.validate(currentState.password, PasswordValidator)
            if (passwordError != null) {
                updateState { state -> state.copy(passwordError = passwordError) }
            }
            val repeatPasswordError = Validator.validate(
                currentState.repeatPassword,
                RepeatPasswordValidator(currentState.password),
            )
            if (repeatPasswordError != null) {
                updateState { state -> state.copy(repeatPasswordError = repeatPasswordError) }
            }
            val birthdateError = Validator.validate(
                currentState.birthdate,
                BirthdateValidator,
            )
            if (birthdateError != null) {
                updateState { state -> state.copy(birthdateError = birthdateError) }
            }
            val genderError = Validator.validate(currentState.gender, GenderValidation)
            if (genderError != null) {
                updateState { state -> state.copy(genderError = genderError) }
            }
            if (emailError != null || nameError != null || surnameError != null || passwordError != null || repeatPasswordError != null || birthdateError != null || currentState.birthdate == null || genderError != null || currentState.gender == null) {
                updateState { state -> state.copy(isLoading = false) }
                return@launch
            }
            registerUseCase(
                RegisterForm(
                    email = currentState.email,
                    name = currentState.name,
                    surname = currentState.surname,
                    password = currentState.password,
                    birthdate = currentState.birthdate,
                    gender = currentState.gender
                )
            ).onEach { result ->
                result.fold(
                    onOk = {
                        updateState { state -> state.copy(isRegistered = true, isLoading = false) }
                    }, onError = { error ->
                        updateState { state ->
                            state.copy(
                                errorMsg = error.type.getMessage(), isLoading = false,
                            )
                        }
                    }
                )
            }.launchIn(this)
        }
    }
}
