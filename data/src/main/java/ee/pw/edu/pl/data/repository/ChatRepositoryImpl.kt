package ee.pw.edu.pl.data.repository

import android.util.Log
import ee.pw.edu.pl.data.datasource.chat.local.ChatLocalDataSource
import ee.pw.edu.pl.data.datasource.chat.remote.ChatRemoteDataSource
import ee.pw.edu.pl.data.model.ApiResponseWithHeaders
import ee.pw.edu.pl.data.model.chat.local.ChatProfileEntity
import ee.pw.edu.pl.data.model.chat.local.MessageEntity
import ee.pw.edu.pl.data.model.chat.remote.MessageResponse
import ee.pw.edu.pl.data.model.chat.remote.SendMessageRequest
import ee.pw.edu.pl.domain.core.result.ResultErrorType
import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.ChatRepository
import ee.pw.edu.pl.domain.usecase.chat.Chat
import ee.pw.edu.pl.domain.usecase.chat.SendMessageForm
import ee.pw.edu.pl.domain.usecase.chat.profile.ChatProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

class ChatRepositoryImpl(
    private val chatRemoteDataSource: ChatRemoteDataSource,
    private val chatLocalDataSource: ChatLocalDataSource,
) : ChatRepository {
    override suspend fun subscribeToChat() {
        withContext(Dispatchers.IO) {
            chatRemoteDataSource.connectToChat().onEach { response ->
                chatLocalDataSource.insertMessages(listOf(response.toEntity()))
            }.launchIn(this)
        }
    }

    override fun getMessages(id: Int): Flow<List<Chat>> =
        chatLocalDataSource.getMessages(id).map { messages ->
            messages.map { message ->
                Chat(text = message.text, isYour = message.fromUser != id)
            }
        }

    override fun sendMessage(form: SendMessageForm) {
        chatRemoteDataSource.sendMessage(
            SendMessageRequest(
                receiverId = form.receiverId,
                message = form.message,
            )
        )
    }

    override fun getProfileChats() =
        chatLocalDataSource.getProfilesWithMessages().map { profilesWithMessages ->
            profilesWithMessages.map { profileWithMessages ->
                val (profile, messages) = profileWithMessages
                ChatProfile(
                    id = profile.id,
                    name = profile.name,
                    avatar = profile.avatar,
                    messages = messages.map { message ->
                        Chat(text = message.text, isYour = message.fromUser == profile.id)
                    },
                )
            }
        }

    override suspend fun updateChatProfiles(): UseCaseResult<Unit> =
        when (val chatProfiles = chatRemoteDataSource.getChats()) {
            is ApiResponseWithHeaders.Error -> UseCaseResult.Error(ResultErrorType.UNKNOWN)
            is ApiResponseWithHeaders.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponseWithHeaders.Ok -> {
                val result = chatProfiles.body
                Log.d("ResponseChats", result.toString())
                chatLocalDataSource.removeAll()
                chatLocalDataSource.insertChatProfiles(
                    result.map { response ->
                        ChatProfileEntity(
                            id = response.id, name = response.name, avatar = response.avatar,
                        )
                    }
                )
                result.forEach { profile ->
                    chatLocalDataSource.insertMessages(
                        profile.messages.map { message -> message.toEntity() }
                    )
                }
                UseCaseResult.Ok(Unit)
            }
        }

    override suspend fun removeChatProfile(id: Int) {
        chatLocalDataSource.removeChatPerson(id)
    }

    private fun MessageResponse.toEntity() = MessageEntity(
        id = id,
        fromUser = fromUser,
        toUser = toUser,
        text = messageText,
        timestamp = sentTimestamp,
    )
}
