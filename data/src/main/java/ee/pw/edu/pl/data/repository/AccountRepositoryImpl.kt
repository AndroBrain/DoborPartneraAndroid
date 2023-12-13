package ee.pw.edu.pl.data.repository

import ee.pw.edu.pl.data.datasource.account.remote.AccountRemoteDataSource
import ee.pw.edu.pl.data.datasource.images.remote.ImageRemoteDataSource
import ee.pw.edu.pl.data.model.ApiResponse
import ee.pw.edu.pl.data.model.account.remote.SetAccountInfoRequest
import ee.pw.edu.pl.data.model.account.toModel
import ee.pw.edu.pl.data.model.account.toRequest
import ee.pw.edu.pl.data.model.profile.remote.ProfileImageUrl
import ee.pw.edu.pl.domain.core.result.ResultErrorType
import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.AccountRepository
import ee.pw.edu.pl.domain.usecase.account.Account
import ee.pw.edu.pl.domain.usecase.account.EditAccountForm
import ee.pw.edu.pl.domain.usecase.profile.ProfileAvatar
import ee.pw.edu.pl.domain.usecase.profile.ProfileImage
import ee.pw.edu.pl.domain.usecase.test.SetTestForm
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AccountRepositoryImpl(
    private val imageRemoteDataSource: ImageRemoteDataSource,
    private val accountRemoteDataSource: AccountRemoteDataSource,
) : AccountRepository {
    override suspend fun update(profileForm: EditAccountForm): UseCaseResult<Unit> {
        val (profileUrl, allImages) = uploadImages(
            avatar = profileForm.profileAvatar,
            images = profileForm.images,
        )
        if (profileUrl == null || allImages.any { it == null }) {
            return UseCaseResult.Error()
        }
        val response = accountRemoteDataSource.setInfo(
            SetAccountInfoRequest(
                avatar = profileUrl,
                description = profileForm.description,
                images = allImages.map { it!!.url },
                interests = profileForm.interests,
            )
        )
        return when (response) {
            is ApiResponse.Error -> UseCaseResult.Error(response.toResultErrorType())
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> UseCaseResult.Ok(Unit)
        }
    }

    override suspend fun get(): UseCaseResult<Account> =
        when (val response = accountRemoteDataSource.getInfo()) {
            is ApiResponse.Error -> UseCaseResult.Error(response.toResultErrorType())
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> UseCaseResult.Ok(response.body.toModel())
        }

    override suspend fun setTest(test: SetTestForm): UseCaseResult<Unit> =
        when (val response = accountRemoteDataSource.setTest(test.toRequest())) {
            is ApiResponse.Error -> UseCaseResult.Error(response.toResultErrorType())
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> UseCaseResult.Ok(Unit)
        }

    private suspend fun uploadImages(
        avatar: ProfileAvatar,
        images: List<ProfileImage>,
    ): Pair<String?, List<ProfileImageUrl?>> = coroutineScope {
        val profileChannel = Channel<String?>()
        launch {
            if (avatar.url != null) {
                profileChannel.send(avatar.url)
            } else if (avatar.bytes != null) {
                val profileUrl = imageRemoteDataSource.uploadImage(
                    bytes = avatar.bytes!!,
                    format = avatar.format,
                ).first()
                profileChannel.send(profileUrl)
            }
        }

        val imagesChannel = Channel<ProfileImageUrl?>()
        for (image in images) {
            launch {
                if (image.url != null) {
                    imagesChannel.send(ProfileImageUrl(url = image.url!!, order = image.order))
                } else if (image.bytes != null) {
                    val imageUrl = imageRemoteDataSource.uploadImage(
                        bytes = image.bytes!!,
                        format = image.format,
                    ).first()
                    if (imageUrl == null) {
                        imagesChannel.send(null)
                    } else {
                        imagesChannel.send(ProfileImageUrl(url = imageUrl, order = image.order))
                    }
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
