package ee.pw.edu.pl.data.datasource.message.remote

import ee.pw.edu.pl.data.model.ApiResponse
import ee.pw.edu.pl.data.model.message.remote.ProfileWithMessagesResponse

interface MessageRemoteDataSource {
    suspend fun getProfilesWithMessages(): ApiResponse<List<ProfileWithMessagesResponse>>
}
