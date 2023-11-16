package ee.pw.edu.pl.data.datasource.message.remote

import ee.pw.edu.pl.data.core.remote.ApiService
import ee.pw.edu.pl.data.model.ApiResponse
import ee.pw.edu.pl.data.model.apiCall
import ee.pw.edu.pl.data.model.message.remote.LoadMoreMessagesRequest
import ee.pw.edu.pl.data.model.message.remote.LoadMoreMessagesResponse

class RetrofitMessageRemoteDataSource(
    private val api: ApiService,
) : MessageRemoteDataSource {
    override suspend fun getProfilesWithMessages() = apiCall {
        api.getConversations()
    }

    override suspend fun loadMoreMessages(request: LoadMoreMessagesRequest): ApiResponse<LoadMoreMessagesResponse> =
        apiCall {
            api.loadMoreMessages(request)
        }
}
