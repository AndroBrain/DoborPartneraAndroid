package ee.pw.edu.pl.data.datasource.match.remote

import ee.pw.edu.pl.data.core.remote.ApiService
import ee.pw.edu.pl.data.model.apiCall
import ee.pw.edu.pl.data.model.match.remote.DeclineMatchRequest
import ee.pw.edu.pl.data.model.match.remote.MatchRequest

class RetrofitMatchRemoteDataSource(
    private val apiService: ApiService,
) : MatchRemoteDataSource {
    override suspend fun getMatches() = apiCall {
        apiService.getMatches()
    }

    override suspend fun decline(request: DeclineMatchRequest) = apiCall {
        apiService.decline(request)
    }

    override suspend fun getMatch(request: MatchRequest) = apiCall {
        apiService.getMatch(request)
    }
}
