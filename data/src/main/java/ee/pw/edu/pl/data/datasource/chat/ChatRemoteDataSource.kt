package ee.pw.edu.pl.data.datasource.chat

import ee.pw.edu.pl.data.model.chat.SendMessageRequest
import kotlinx.coroutines.flow.Flow

interface ChatRemoteDataSource {
    fun connectToChat(): Flow<String>
    fun sendMessage(request: SendMessageRequest)
}
