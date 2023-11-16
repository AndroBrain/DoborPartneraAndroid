package ee.pw.edu.pl.data.datasource.chat.remote

import ee.pw.edu.pl.data.model.message.remote.MessageResponse
import ee.pw.edu.pl.data.model.message.remote.SendMessageRequest
import kotlinx.coroutines.flow.Flow

interface ChatRemoteDataSource {
    fun connectToChat(): Flow<MessageResponse>
    fun sendMessage(request: SendMessageRequest)
}
