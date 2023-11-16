package ee.pw.edu.pl.data.datasource.message.remote

import ee.pw.edu.pl.data.core.remote.ApiService
import ee.pw.edu.pl.data.model.apiCall

class RetrofitMessageRemoteDataSource(
    private val api: ApiService,
) : MessageRemoteDataSource {
    override suspend fun getProfilesWithMessages() = apiCall {
        api.getConversations()
    }
}
