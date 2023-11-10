package ee.pw.edu.pl.data.datasource.match

import ee.pw.edu.pl.data.core.remote.ApiService
import ee.pw.edu.pl.data.model.apiCallWithHeaders

class RetrofitMatchRemoteDataSource(
    private val apiService: ApiService,
) : MatchRemoteDataSource {
    override suspend fun getMatches() = apiCallWithHeaders {
        apiService.getMatches()
    }
}
