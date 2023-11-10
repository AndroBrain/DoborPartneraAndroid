package ee.pw.edu.pl.domain.usecase.match

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.MatchRepository
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(
    private val matchRepository: MatchRepository,
) {
    suspend operator fun invoke(): UseCaseResult<List<MatchProfile>> = matchRepository.getMatches()
}
