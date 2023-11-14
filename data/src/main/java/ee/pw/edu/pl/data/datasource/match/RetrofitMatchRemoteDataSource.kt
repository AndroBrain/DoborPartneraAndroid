package ee.pw.edu.pl.data.datasource.match

import ee.pw.edu.pl.data.core.remote.ApiService
import ee.pw.edu.pl.data.model.apiCallWithHeaders
import ee.pw.edu.pl.data.model.match.DeclineMatchRequest
import ee.pw.edu.pl.data.model.match.MatchRequest

class RetrofitMatchRemoteDataSource(
    private val apiService: ApiService,
) : MatchRemoteDataSource {
    override suspend fun getMatches() = apiCallWithHeaders {
        apiService.getMatches()
    }

    override suspend fun decline(request: DeclineMatchRequest) = apiCallWithHeaders {
        apiService.decline(request)
    }

    override suspend fun getMatch(request: MatchRequest) = apiCallWithHeaders {
        apiService.getMatch(request)
    }
}
