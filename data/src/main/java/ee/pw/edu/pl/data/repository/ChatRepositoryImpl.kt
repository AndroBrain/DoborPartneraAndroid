package ee.pw.edu.pl.data.repository

import ee.pw.edu.pl.data.datasource.chat.ChatRemoteDataSource
import ee.pw.edu.pl.domain.repository.ChatRepository

class ChatRepositoryImpl(
    private val chatRemoteDataSource: ChatRemoteDataSource
) : ChatRepository {
    override fun getChat() = chatRemoteDataSource.connectToChat()
}
