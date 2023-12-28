package ee.pw.edu.pl.data.repository

import ee.pw.edu.pl.data.datasource.message.local.MessageLocalDataSource
import ee.pw.edu.pl.data.datasource.message.remote.MessageRemoteDataSource
import ee.pw.edu.pl.data.model.ApiResponse
import ee.pw.edu.pl.data.model.message.remote.LoadMoreMessagesRequest
import ee.pw.edu.pl.data.model.message.toEntities
import ee.pw.edu.pl.data.model.message.toModels
import ee.pw.edu.pl.data.model.message.toProfileModels
import ee.pw.edu.pl.domain.core.result.ResultErrorType
import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.MessageRepository
import ee.pw.edu.pl.domain.repository.ProfileRepository
import ee.pw.edu.pl.domain.usecase.message.Message
import ee.pw.edu.pl.domain.usecase.message.profile.ProfileWithMessages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class MessageRepositoryImpl(
    private val messageRemoteDataSource: MessageRemoteDataSource,
    private val messageLocalDataSource: MessageLocalDataSource,
    private val profileRepository: ProfileRepository,
) : MessageRepository {
    override fun getMessages(id: Int): Flow<List<Message>> = messageLocalDataSource
        .get(id)
        .map { messages -> messages.toModels(id) }

    override fun getProfilesWithMessages(): Flow<List<ProfileWithMessages>> =
        messageLocalDataSource.getProfilesWithMessages()
            .map { profilesWithMessages -> profilesWithMessages.toModels() }

    override suspend fun updateProfilesWithMessages(): UseCaseResult<Unit> =
        when (val response = messageRemoteDataSource.getProfilesWithMessages()) {
            is ApiResponse.Error -> UseCaseResult.Error(response.toResultErrorType())
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> {
                val profiles = response.body
                messageLocalDataSource.removeAll()
                profileRepository.removeAll()
                profileRepository.insert(profiles.toProfileModels())
                profiles.forEach { profile ->
                    messageLocalDataSource.insert(profile.messages.toEntities())
                }
                UseCaseResult.Ok(Unit)
            }
        }

    override suspend fun loadMoreMessages(id: Int): UseCaseResult<Boolean> {
        val message = messageLocalDataSource.get(id).first().firstOrNull()
            ?: return UseCaseResult.Ok(false)
        return when (
            val response = messageRemoteDataSource.loadMoreMessages(
                LoadMoreMessagesRequest(
                    id = id,
                    lastMessageTimestamp = message.timestamp,
                ),
            )
        ) {
            is ApiResponse.Error -> UseCaseResult.Error(response.toResultErrorType())
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> {
                val messages = response.body.messages
                messageLocalDataSource.insert(messages.toEntities())
                UseCaseResult.Ok(response.body.canLoadMore)
            }
        }
    }
}
