package ee.pw.edu.pl.data.datasource.message.remote

import ee.pw.edu.pl.data.model.ApiResponse
import ee.pw.edu.pl.data.model.chat.remote.ChatProfileResponse

interface MessageRemoteDataSource {
    suspend fun getChats(): ApiResponse<List<ChatProfileResponse>>
}
