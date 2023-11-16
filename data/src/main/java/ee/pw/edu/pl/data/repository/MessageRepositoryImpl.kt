package ee.pw.edu.pl.data.repository

import ee.pw.edu.pl.data.datasource.message.local.MessageLocalDataSource
import ee.pw.edu.pl.data.datasource.message.remote.MessageRemoteDataSource
import ee.pw.edu.pl.data.model.ApiResponse
import ee.pw.edu.pl.data.model.message.local.MessageEntity
import ee.pw.edu.pl.data.model.message.remote.LoadMoreMessagesRequest
import ee.pw.edu.pl.data.model.message.remote.MessageResponse
import ee.pw.edu.pl.domain.core.result.ResultErrorType
import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.MessageRepository
import ee.pw.edu.pl.domain.repository.ProfileRepository
import ee.pw.edu.pl.domain.usecase.message.Message
import ee.pw.edu.pl.domain.usecase.message.profile.ProfileWithMessages
import ee.pw.edu.pl.domain.usecase.profile.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class MessageRepositoryImpl(
    private val messageRemoteDataSource: MessageRemoteDataSource,
    private val messageLocalDataSource: MessageLocalDataSource,
    private val profileRepository: ProfileRepository,
) : MessageRepository {
    override fun getMessages(id: Int): Flow<List<Message>> =
        messageLocalDataSource.get(id).map { messages ->
            messages.map { message ->
                Message(text = message.text, isYour = message.fromUser != id)
            }
        }

    override fun getProfilesWithMessages(): Flow<List<ProfileWithMessages>> =
        messageLocalDataSource.getProfilesWithMessages().map { profilesWithMessages ->
            profilesWithMessages.map { profileWithMessages ->
                val (profile, messages) = profileWithMessages
                ProfileWithMessages(
                    id = profile.id,
                    name = profile.name,
                    avatar = profile.avatar,
                    messages = messages.map { message ->
                        Message(text = message.text, isYour = message.fromUser == profile.id)
                    },
                )
            }
        }

    override suspend fun updateChatProfiles(): UseCaseResult<Unit> =
        when (val chatProfiles = messageRemoteDataSource.getProfilesWithMessages()) {
            is ApiResponse.Error -> UseCaseResult.Error(ResultErrorType.UNKNOWN)
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> {
                val result = chatProfiles.body
                messageLocalDataSource.removeAll()
                profileRepository.removeAll()
                profileRepository.insert(
                    result.map { response ->
                        Profile(
                            id = response.id, name = response.name, avatar = response.avatar,
                        )
                    }
                )
                result.forEach { profile ->
                    messageLocalDataSource.insert(
                        profile.messages.map { message -> message.toEntity() }
                    )
                }
                UseCaseResult.Ok(Unit)
            }
        }

    override suspend fun loadMoreMessages(id: Int): UseCaseResult<Boolean> {
        val message = messageLocalDataSource.get(id).first().first()
        return when (
            val response = messageRemoteDataSource.loadMoreMessages(
                LoadMoreMessagesRequest(
                    id = id,
                    lastMessageTimestamp = message.timestamp,
                ),
            )
        ) {
            is ApiResponse.Error -> UseCaseResult.Error(ResultErrorType.UNKNOWN)
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.UNKNOWN)
            is ApiResponse.Ok -> {
                val messages = response.body.messages
                messageLocalDataSource.insert(messages.map { it.toEntity() })
                UseCaseResult.Ok(response.body.canLoadMore)
            }
        }
    }

    private fun MessageResponse.toEntity() = MessageEntity(
        id = id,
        fromUser = fromUser,
        toUser = toUser,
        text = messageText,
        timestamp = sentTimestamp,
    )
}
