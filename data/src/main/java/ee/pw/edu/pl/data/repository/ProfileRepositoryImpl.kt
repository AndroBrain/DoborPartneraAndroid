package ee.pw.edu.pl.data.repository

import android.util.Log
import ee.pw.edu.pl.data.datasource.images.ImageRemoteDataSource
import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.ProfileRepository
import ee.pw.edu.pl.domain.usecase.profile.EditProfileForm
import kotlinx.coroutines.flow.first

class ProfileRepositoryImpl(
    private val imageRemoteDataSource: ImageRemoteDataSource,
) : ProfileRepository {
    override suspend fun updateProfile(profileForm: EditProfileForm): UseCaseResult<Unit> {
        val profileUrl = imageRemoteDataSource.uploadImage(profileForm.profileImage).first()

        val imagesUrl = profileForm.images.map { image ->
            imageRemoteDataSource.uploadImage(image).first()
        }
        if (profileUrl == null || imagesUrl.any { it == null }) {
//            TODO handle error
            Log.e("ProfileRepository", "profile or images are null $profileUrl $imagesUrl")
        }
//        TODO send the urls with the data to the server
        return UseCaseResult.Ok(Unit)
    }
}
