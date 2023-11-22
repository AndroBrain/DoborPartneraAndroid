package ee.pw.edu.pl.domain.usecase.message.profile

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.ProfileRepository
import ee.pw.edu.pl.domain.usecase.match.DeclineMatchUseCase

class RemoveProfileWithMessagesUseCase(
    private val declineMatchUseCase: DeclineMatchUseCase,
    private val profileRepository: ProfileRepository,
) {
    suspend operator fun invoke(id: Int): UseCaseResult<Unit> {
        val result = declineMatchUseCase(id)
        if (result is UseCaseResult.Ok) {
            profileRepository.remove(id)
        }
        return result
    }
}
