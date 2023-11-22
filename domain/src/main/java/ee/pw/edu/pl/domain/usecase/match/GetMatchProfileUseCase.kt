package ee.pw.edu.pl.domain.usecase.match

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.MatchRepository

class GetMatchProfileUseCase(private val matchRepository: MatchRepository) {
    suspend operator fun invoke(id: Int): UseCaseResult<MatchProfile> = matchRepository.getMatch(id)
}
