package ee.pw.edu.pl.data.repository

import ee.pw.edu.pl.data.datasource.images.ImageRemoteDataSource
import ee.pw.edu.pl.data.datasource.profile.ProfileRemoteDataSource
import ee.pw.edu.pl.data.model.ApiResponseWithHeaders
import ee.pw.edu.pl.data.model.profile.ProfileImageUrl
import ee.pw.edu.pl.data.model.profile.SetProfileInfoRequest
import ee.pw.edu.pl.domain.core.result.ResultErrorType
import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.ProfileRepository
import ee.pw.edu.pl.domain.usecase.profile.EditProfileForm
import ee.pw.edu.pl.domain.usecase.profile.ProfileAvatar
import ee.pw.edu.pl.domain.usecase.profile.ProfileImage
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProfileRepositoryImpl(
    private val imageRemoteDataSource: ImageRemoteDataSource,
    private val profileRemoteDataSource: ProfileRemoteDataSource,
) : ProfileRepository {
    override suspend fun updateProfile(profileForm: EditProfileForm): UseCaseResult<Unit> {
        val (profileUrl, allImages) = uploadImages(
            avatar = profileForm.profileAvatar,
            images = profileForm.images,
        )
        if (profileUrl == null || allImages.any { it == null }) {
            return UseCaseResult.Error()
        }
        val response = profileRemoteDataSource.setInfo(
            SetProfileInfoRequest(
                avatarUrl = profileUrl, description = profileForm.description,
            )
        )
        return when (response) {
            is ApiResponseWithHeaders.Error -> UseCaseResult.Error(ResultErrorType.EMAIL_TAKEN)
            is ApiResponseWithHeaders.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponseWithHeaders.Ok -> UseCaseResult.Ok(Unit)
        }
    }

    private suspend fun uploadImages(
        avatar: ProfileAvatar,
        images: List<ProfileImage>
    ): Pair<String?, List<ProfileImageUrl?>> = coroutineScope {
        val profileChannel = Channel<String?>()
        launch {
            val profileUrl = imageRemoteDataSource.uploadImage(
                bytes = avatar.bytes,
                format = avatar.format,
            ).first()
            profileChannel.send(profileUrl)
        }

        val imagesChannel = Channel<ProfileImageUrl?>()
        for (image in images) {
            launch {
                val imageUrl = imageRemoteDataSource.uploadImage(
                    bytes = image.bytes,
                    format = image.format,
                ).first()
                if (imageUrl == null) {
                    imagesChannel.send(null)
                } else {
                    imagesChannel.send(ProfileImageUrl(url = imageUrl, order = image.order))
                }
            }
        }
        val profileUrl = profileChannel.receive()

        var allImages = emptyList<ProfileImageUrl?>()
        repeat(images.size) {
            val imageUrl = imagesChannel.receive()
            allImages = allImages + imageUrl
        }
        allImages = allImages.sortedBy { it?.order }
        Pair(profileUrl, allImages)
    }
}
