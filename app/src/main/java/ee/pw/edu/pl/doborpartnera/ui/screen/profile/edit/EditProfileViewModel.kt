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

private const val DESCRIPTION_MIN_LENGTH = 60
const val DESCRIPTION_MAX_LENGTH = 240

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
                description = state.description.ifEmpty { editProfile.description },
            )
        }
    }

    fun clearErrorMsg() {
        updateState { state -> state.copy(errorMsg = null) }
    }

    fun updateDescription(description: String) {
        updateState { state -> state.copy(description = description, descriptionError = null) }
    }

    fun save() {
        updateState { state -> state.copy(isLoading = true) }
        val currentState = state.value
        val descriptionError = Validator.validate(
            currentState.description,
            NameLengthValidator(
                errorMessage = R.string.validation_err_short_description_length,
                min = DESCRIPTION_MIN_LENGTH,
                max = DESCRIPTION_MAX_LENGTH,
            )
        )
        if (descriptionError != null) {
            updateState { state -> state.copy(descriptionError = descriptionError) }
        }
        if (descriptionError == null) {
            editProfile(currentState)
        } else {
            updateState { state -> state.copy(isLoading = false) }
        }
    }

    private fun editProfile(state: EditProfileState) {
        viewModelScope.launch {
            editProfileUseCase(
                EditProfileForm(description = state.description)
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
