package ee.pw.edu.pl.data.datasource.match

import ee.pw.edu.pl.data.model.ApiResponseWithHeaders
import ee.pw.edu.pl.data.model.match.MatchResponse

interface MatchRemoteDataSource {
    suspend fun getMatches(): ApiResponseWithHeaders<List<MatchResponse>>
}
