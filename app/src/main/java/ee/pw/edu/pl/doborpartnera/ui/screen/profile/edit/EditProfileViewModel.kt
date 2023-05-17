package ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.core.result.getMessage
import ee.pw.edu.pl.doborpartnera.core.validation.NameLengthValidator
import ee.pw.edu.pl.doborpartnera.core.validation.Validator
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.domain.core.result.fold
import ee.pw.edu.pl.domain.usecase.profile.EditProfileForm
import ee.pw.edu.pl.domain.usecase.profile.EditProfileUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val editProfileUseCase: EditProfileUseCase,
    savedStateHandle: SavedStateHandle
) : SingleStateViewModel<EditProfileState>(savedStateHandle, EditProfileState()) {
    private val args = EditProfileArgs(savedStateHandle)

    init {
        val editProfile = args.editProfile
        updateState { state ->
            state.copy(
                name = state.name.ifEmpty { editProfile.name },
                surname = state.surname.ifEmpty { editProfile.surname },
                description = state.description.ifEmpty { editProfile.description },
            )
        }
    }

    fun clearErrorMsg() {
        updateState { state -> state.copy(errorMsg = null) }
    }

    fun updateName(name: String) {
        updateState { state -> state.copy(name = name) }
    }

    fun updateSurname(surname: String) {
        updateState { state -> state.copy(surname = surname) }
    }

    fun updateDescription(description: String) {
        updateState { state -> state.copy(description = description) }
    }

    fun save() {
        updateState { state -> state.copy(isLoading = true) }
        val currentState = state.value
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
        val descriptionError = Validator.validate(
            currentState.description,
            NameLengthValidator(R.string.validation_err_short_description_length)
        )
        if (descriptionError != null) {
            updateState { state -> state.copy(descriptionError = descriptionError) }
        }
        if (nameError == null && surnameError == null && descriptionError == null) {
            editProfile(currentState)
        } else {
            updateState { state -> state.copy(isLoading = false) }
        }
    }

    private fun editProfile(state: EditProfileState) {
        viewModelScope.launch {
            editProfileUseCase(
                EditProfileForm(
                    name = state.name,
                    surname = state.surname,
                    description = state.description,
                )
            ).onEach { result ->
                result.fold(
                    onOk = {
                        updateState { state -> state.copy(isLoading = false, isSaved = true) }
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
