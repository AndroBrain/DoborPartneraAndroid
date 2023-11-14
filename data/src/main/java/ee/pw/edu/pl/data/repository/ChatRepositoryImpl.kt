package ee.pw.edu.pl.data.repository

import ee.pw.edu.pl.data.datasource.chat.local.ChatLocalDataSource
import ee.pw.edu.pl.data.datasource.chat.remote.ChatRemoteDataSource
import ee.pw.edu.pl.data.model.chat.remote.SendMessageRequest
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
}
