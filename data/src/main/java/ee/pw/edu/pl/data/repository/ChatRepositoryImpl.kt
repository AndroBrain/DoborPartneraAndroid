package ee.pw.edu.pl.data.repository

import android.util.Log
import ee.pw.edu.pl.data.datasource.chat.local.ChatLocalDataSource
import ee.pw.edu.pl.data.datasource.chat.remote.ChatRemoteDataSource
import ee.pw.edu.pl.data.model.ApiResponseWithHeaders
import ee.pw.edu.pl.data.model.chat.local.ChatProfileEntity
import ee.pw.edu.pl.data.model.chat.remote.SendMessageRequest
import ee.pw.edu.pl.domain.core.result.ResultErrorType
import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.ChatRepository
import ee.pw.edu.pl.domain.usecase.chat.SendMessageForm
import ee.pw.edu.pl.domain.usecase.chat.profile.ChatMessage
import ee.pw.edu.pl.domain.usecase.chat.profile.ChatProfile
import kotlinx.coroutines.flow.map

class ChatRepositoryImpl(
    private val chatRemoteDataSource: ChatRemoteDataSource,
    private val chatLocalDataSource: ChatLocalDataSource,
) : ChatRepository {
    override fun getChat() = chatRemoteDataSource.connectToChat()
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
                        ChatMessage(id = message.id, ownerId = message.ownerId, text = message.text)
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
                chatLocalDataSource.insertChatProfiles(
                    result.map { response ->
                        ChatProfileEntity(
                            id = response.id, name = response.name, avatar = response.avatar,
                        )
                    }
                )
                UseCaseResult.Ok(Unit)
            }
        }

    override suspend fun removeChatProfile(id: Int) {
        chatLocalDataSource.removeChatPerson(id)
    }
}
