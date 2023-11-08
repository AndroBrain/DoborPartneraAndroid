package ee.pw.edu.pl.data.repository

import android.util.Log
import ee.pw.edu.pl.data.datasource.images.ImageRemoteDataSource
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
            Log.d("ProfileRepository", "starting upload")
            val profileChannel = Channel<String?>()
            launch {
                val profileUrl = imageRemoteDataSource.uploadImage(profileForm.profileImage).first()
                profileChannel.send(profileUrl)
            }

            val imagesChannel = Channel<String?>()
            for (image in profileForm.images) {
                launch {
                    val imageUrl = imageRemoteDataSource.uploadImage(image).first()
                    imagesChannel.send(imageUrl)
                }
            }
            val profileUrl = profileChannel.receive()
            Log.d("ProfileRepository", "profile $profileUrl")

            var allImages = emptyList<String?>()
            repeat(profileForm.images.size) {
                val imageUrl = imagesChannel.receive()
                Log.d("ProfileRepository", "received $imageUrl")
                allImages = allImages + imageUrl
            }
            if (profileUrl == null || allImages.any { it == null }) {
//            TODO handle error
                Log.e("ProfileRepository", "profile or images are null $profileUrl $allImages")
            }
//        TODO send the urls with the data to the server
            UseCaseResult.Ok(Unit)
        }
}
