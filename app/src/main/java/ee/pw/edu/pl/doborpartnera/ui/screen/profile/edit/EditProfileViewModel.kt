package ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit

import android.net.Uri
import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.core.bitmap.BitmapManager
import ee.pw.edu.pl.doborpartnera.core.result.getMessage
import ee.pw.edu.pl.doborpartnera.core.validation.CollectionSizeValidator
import ee.pw.edu.pl.doborpartnera.core.validation.FieldNotNullValidator
import ee.pw.edu.pl.doborpartnera.core.validation.NameLengthValidator
import ee.pw.edu.pl.doborpartnera.core.validation.Validator
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.domain.core.result.fold
import ee.pw.edu.pl.domain.usecase.account.EditAccountForm
import ee.pw.edu.pl.domain.usecase.account.EditAccountUseCase
import ee.pw.edu.pl.domain.usecase.profile.ProfileAvatar
import ee.pw.edu.pl.domain.usecase.profile.ProfileImage
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private const val DESCRIPTION_MIN_LENGTH = 60
const val DESCRIPTION_MAX_LENGTH = 240

private const val PROFILE_IMAGE_SIZE = 320

const val MIN_INTERESTS = 2
const val MAX_INTERESTS = 5

const val MIN_IMAGES = 4
const val MAX_IMAGES = 8

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val editAccountUseCase: EditAccountUseCase,
    private val bitmapManager: BitmapManager,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<EditProfileState>(savedStateHandle, EditProfileState()) {
    private val args = EditProfileArgs(savedStateHandle)

    init {
        updateState { state ->
            state.copy(
                avatar = args.avatar,
                images = args.images?.toSet().orEmpty(),
                description = args.description.orEmpty(),
                interests = args.interests.orEmpty(),
            )
        }
    }

    fun clearErrorMsg() {
        updateState { state -> state.copy(errorMsg = null) }
    }

    fun clearInterestsError() {
        updateState { state -> state.copy(interestsError = null) }
    }

    fun updateProfileImage(uri: Uri) {
        updateState { state -> state.copy(avatar = uri.toString(), avatarError = null) }
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
                        error = Validator.validate(
                            this,
                            CollectionSizeValidator(
                                errorMessage = R.string.profile_err_too_many_images,
                                max = MAX_IMAGES,
                            )
                        )
                        if (error == null) {
                            add(image.toString())
                        }
                    }
                },
                imagesError = error,
            )
        }
    }

    fun deleteImage(image: String) {
        updateState { state -> state.copy(images = state.images - image) }
    }

    fun save(interests: List<String>) {
        updateState { state -> state.copy(isLoading = true) }
        val currentState = state.value
        val avatarError = Validator.validate(
            currentState.avatar,
            FieldNotNullValidator(errorMessage = R.string.profile_err_no_avatar),
        )
        val imagesError = Validator.validate(
            currentState.images,
            CollectionSizeValidator(
                errorMessage = R.string.profile_err_images,
                min = MIN_IMAGES,
                max = MAX_IMAGES,
            ),
        )
        val descriptionError = Validator.validate(
            currentState.description,
            NameLengthValidator(
                errorMessage = R.string.validation_err_short_description_length,
                min = DESCRIPTION_MIN_LENGTH,
                max = DESCRIPTION_MAX_LENGTH,
            )
        )
        val interestsError = Validator.validate(
            interests,
            CollectionSizeValidator(
                errorMessage = R.string.profile_err_interests,
                min = MIN_INTERESTS,
                max = MAX_INTERESTS
            )
        )
        if (avatarError == null && imagesError == null && descriptionError == null && interestsError == null) {
            editProfile(currentState, interests)
        } else {
            updateState { state ->
                state.copy(
                    isLoading = false,
                    avatarError = avatarError,
                    imagesError = imagesError,
                    descriptionError = descriptionError,
                    interestsError = interestsError,
                )
            }
        }
    }

    private fun editProfile(state: EditProfileState, interests: List<String>) {
        viewModelScope.launch {
            val avatarUri = Uri.parse(state.avatar) ?: return@launch
            val format = "jpg"
            val profileBytes = bitmapManager.compress(
                uri = avatarUri,
                width = PROFILE_IMAGE_SIZE,
                height = PROFILE_IMAGE_SIZE,
            )
            editAccountUseCase(
                EditAccountForm(
                    profileAvatar = ProfileAvatar(
                        bytes = profileBytes,
                        url = if (profileBytes == null) state.avatar else null,
                        format = format,
                    ),
                    description = state.description,
                    images = state.images.mapIndexed { index, uri ->
                        val bytes = bitmapManager.compress(Uri.parse(uri))
                        ProfileImage(
                            order = index,
                            bytes = bytes,
                            url = if (bytes == null) uri else null,
                            format = format,
                        )
                    },
                    interests = interests,
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
