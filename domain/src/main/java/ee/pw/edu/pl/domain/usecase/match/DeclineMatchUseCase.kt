package ee.pw.edu.pl.domain.usecase.match

import ee.pw.edu.pl.domain.repository.MatchRepository

class DeclineMatchUseCase(private val matchRepository: MatchRepository) {
    suspend operator fun invoke(id: Int) = matchRepository.decline(id)
}
