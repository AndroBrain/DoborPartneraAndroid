package ee.pw.edu.pl.domain.repository

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.usecase.match.MatchProfile

interface MatchRepository {
    suspend fun getMatches(): UseCaseResult<List<MatchProfile>>
}
