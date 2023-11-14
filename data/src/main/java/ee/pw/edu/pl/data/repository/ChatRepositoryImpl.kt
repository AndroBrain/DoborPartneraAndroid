package ee.pw.edu.pl.data.repository

import ee.pw.edu.pl.data.datasource.chat.ChatRemoteDataSource
import ee.pw.edu.pl.data.model.chat.SendMessageRequest
import ee.pw.edu.pl.domain.repository.ChatRepository
import ee.pw.edu.pl.domain.usecase.chat.SendMessageForm

class ChatRepositoryImpl(
    private val chatRemoteDataSource: ChatRemoteDataSource
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
}
