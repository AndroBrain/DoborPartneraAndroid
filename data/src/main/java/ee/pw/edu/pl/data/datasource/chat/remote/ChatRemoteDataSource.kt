package ee.pw.edu.pl.data.datasource.chat.remote

import ee.pw.edu.pl.data.model.ApiResponseWithHeaders
import ee.pw.edu.pl.data.model.chat.remote.ChatProfileResponse
import ee.pw.edu.pl.data.model.chat.remote.SendMessageRequest
import kotlinx.coroutines.flow.Flow

interface ChatRemoteDataSource {
    fun connectToChat(): Flow<String>
    fun sendMessage(request: SendMessageRequest)
    suspend fun getChats(): ApiResponseWithHeaders<List<ChatProfileResponse>>
}
