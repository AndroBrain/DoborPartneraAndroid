package ee.pw.edu.pl.data.datasource.match

import ee.pw.edu.pl.data.model.ApiResponseWithHeaders
import ee.pw.edu.pl.data.model.match.DeclineMatchRequest
import ee.pw.edu.pl.data.model.match.MatchRequest
import ee.pw.edu.pl.data.model.match.MatchResponse

interface MatchRemoteDataSource {
    suspend fun getMatches(): ApiResponseWithHeaders<List<MatchResponse>>
    suspend fun decline(request: DeclineMatchRequest): ApiResponseWithHeaders<Unit>
    suspend fun getMatch(request: MatchRequest): ApiResponseWithHeaders<MatchResponse>
}
