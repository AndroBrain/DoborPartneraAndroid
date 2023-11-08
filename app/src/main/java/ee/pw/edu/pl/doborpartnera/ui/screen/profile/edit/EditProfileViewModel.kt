package ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit

import android.net.Uri
import androidx.annotation.StringRes
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

const val MIN_INTERESTS = 2
const val MAX_INTERESTS = 5

const val MIN_IMAGES = 4
const val MAX_IMAGES = 8

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val editProfileUseCase: EditProfileUseCase,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<EditProfileState>(savedStateHandle, EditProfileState()) {

    fun clearErrorMsg() {
        updateState { state -> state.copy(errorMsg = null) }
    }

    fun clearInterestsError() {
        updateState { state -> state.copy(interestsError = null) }
    }

    fun updateProfileImage(uri: Uri) {
        updateState { state -> state.copy(profileImageUrl = uri.toString()) }
    }

    fun updateDescription(description: String) {
        updateState { state -> state.copy(description = description, descriptionError = null) }
    }

    fun addImages(images: List<Uri>) {
        updateState { state ->
            @StringRes var error: Int? = null
            state.copy(
                images = state.images.toMutableSet().apply {
                    images.forEach { image ->
                        if (size < MAX_IMAGES) {
                            add(image.toString())
                        } else {
                            error = R.string.profile_err_too_many_images
                        }
                    }
                },
                errorMsg = error,
            )
        }
    }

    fun deleteImage(image: String) {
        updateState { state -> state.copy(images = state.images - image) }
    }

    fun save(interests: List<String>) {
        updateState { state -> state.copy(isLoading = true) }
        val currentState = state.value
        if (currentState.profileImageUrl == null) {
            updateState { state ->
                state.copy(
                    errorMsg = R.string.profile_err_no_avatar,
                    isLoading = false
                )
            }
            return
        }
        if (currentState.images.size < MIN_IMAGES || currentState.images.size > MAX_IMAGES) {
            updateState { state ->
                state.copy(
                    errorMsg = R.string.profile_err_images,
                    isLoading = false
                )
            }
            return
        }
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
        val interestsError = if (interests.size < MIN_INTERESTS || interests.size > MAX_INTERESTS) {
            R.string.profile_err_interests
        } else {
            null
        }
        if (interestsError != null) {
            updateState { state -> state.copy(interestsError = interestsError) }
        }
        if (descriptionError == null && interestsError == null) {
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
