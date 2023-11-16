package ee.pw.edu.pl.data.datasource.chat.remote

import ee.pw.edu.pl.data.model.ApiResponse
import ee.pw.edu.pl.data.model.chat.remote.ChatProfileResponse
import ee.pw.edu.pl.data.model.chat.remote.MessageResponse
import ee.pw.edu.pl.data.model.chat.remote.SendMessageRequest
import kotlinx.coroutines.flow.Flow

interface ChatRemoteDataSource {
    fun connectToChat(): Flow<MessageResponse>
    fun sendMessage(request: SendMessageRequest)
    suspend fun getChats(): ApiResponse<List<ChatProfileResponse>>
}
