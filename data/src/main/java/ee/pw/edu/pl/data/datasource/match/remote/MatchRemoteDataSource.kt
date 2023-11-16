package ee.pw.edu.pl.data.datasource.match.remote

import ee.pw.edu.pl.data.model.ApiResponse
import ee.pw.edu.pl.data.model.match.DeclineMatchRequest
import ee.pw.edu.pl.data.model.match.MatchRequest
import ee.pw.edu.pl.data.model.match.MatchResponse

interface MatchRemoteDataSource {
    suspend fun getMatches(): ApiResponse<List<MatchResponse>>
    suspend fun decline(request: DeclineMatchRequest): ApiResponse<Unit>
    suspend fun getMatch(request: MatchRequest): ApiResponse<MatchResponse>
}
