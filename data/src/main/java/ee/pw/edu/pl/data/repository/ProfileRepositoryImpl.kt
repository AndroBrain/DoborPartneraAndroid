package ee.pw.edu.pl.data.repository

import ee.pw.edu.pl.data.datasource.images.ImageRemoteDataSource
import ee.pw.edu.pl.data.model.profile.ProfileImageUrl
import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.ProfileRepository
import ee.pw.edu.pl.domain.usecase.profile.EditProfileForm
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProfileRepositoryImpl(
    private val imageRemoteDataSource: ImageRemoteDataSource,
) : ProfileRepository {
    override suspend fun updateProfile(profileForm: EditProfileForm): UseCaseResult<Unit> =
        coroutineScope {
            val profileChannel = Channel<String?>()
            launch {
                val profileUrl = imageRemoteDataSource.uploadImage(
                    bytes = profileForm.profileAvatar.bytes,
                    format = profileForm.profileAvatar.format,
                ).first()
                profileChannel.send(profileUrl)
            }

            val imagesChannel = Channel<ProfileImageUrl?>()
            for (image in profileForm.images) {
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
            repeat(profileForm.images.size) {
                val imageUrl = imagesChannel.receive()
                allImages = allImages + imageUrl
            }
            if (profileUrl == null || allImages.any { it == null }) {
                return@coroutineScope UseCaseResult.Error()
            }
            allImages = allImages.sortedBy { it?.order }
//        TODO send the urls with the data to the server
            UseCaseResult.Ok(Unit)
        }
}
