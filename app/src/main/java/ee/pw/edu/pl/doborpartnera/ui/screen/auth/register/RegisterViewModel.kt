package ee.pw.edu.pl.doborpartnera.ui.screen.auth.register

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.core.validation.EmailValidator
import ee.pw.edu.pl.doborpartnera.core.validation.NameLengthValidator
import ee.pw.edu.pl.doborpartnera.core.validation.Validator
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
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
        updateState { state -> state.copy(password = password) }
    }

    fun changeRepeatPassword(repeatPassword: String) {
        updateState { state -> state.copy(repeatPassword = repeatPassword) }
    }

    fun register() {
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
            currentState.name,
            NameLengthValidator(R.string.validation_err_surname_length)
        )
        if (surnameError != null) {
            updateState { state -> state.copy(surnameError = surnameError) }
        }
    }

}
