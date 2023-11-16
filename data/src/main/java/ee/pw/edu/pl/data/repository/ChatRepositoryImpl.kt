package ee.pw.edu.pl.data.repository

import ee.pw.edu.pl.data.datasource.chat.remote.ChatRemoteDataSource
import ee.pw.edu.pl.data.datasource.message.local.MessageLocalDataSource
import ee.pw.edu.pl.data.model.message.local.MessageEntity
import ee.pw.edu.pl.data.model.message.remote.MessageResponse
import ee.pw.edu.pl.data.model.message.remote.SendMessageRequest
import ee.pw.edu.pl.domain.repository.ChatRepository
import ee.pw.edu.pl.domain.usecase.chat.SendMessageForm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

class ChatRepositoryImpl(
    private val chatRemoteDataSource: ChatRemoteDataSource,
    private val messageLocalDataSource: MessageLocalDataSource,
) : ChatRepository {
    override suspend fun subscribeToChat() {
        withContext(Dispatchers.IO) {
            chatRemoteDataSource.connectToChat().onEach { response ->
                messageLocalDataSource.insert(listOf(response.toEntity()))
            }.launchIn(this)
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

    private fun MessageResponse.toEntity() = MessageEntity(
        id = id,
        fromUser = fromUser,
        toUser = toUser,
        text = messageText,
        timestamp = sentTimestamp,
    )
}
