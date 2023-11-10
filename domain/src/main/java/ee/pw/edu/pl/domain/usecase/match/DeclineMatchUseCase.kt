package ee.pw.edu.pl.domain.usecase.match

import ee.pw.edu.pl.domain.repository.MatchRepository
import javax.inject.Inject

class DeclineMatchUseCase @Inject constructor(
    private val matchRepository: MatchRepository,
) {
    suspend operator fun invoke(id: Int) = matchRepository.decline(id)
}
