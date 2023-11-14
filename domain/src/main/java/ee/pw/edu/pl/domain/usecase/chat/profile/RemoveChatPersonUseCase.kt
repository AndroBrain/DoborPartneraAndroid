package ee.pw.edu.pl.domain.usecase.chat.profile

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.ChatRepository
import ee.pw.edu.pl.domain.usecase.match.DeclineMatchUseCase
import javax.inject.Inject

class RemoveChatPersonUseCase @Inject constructor(
    private val declineMatchUseCase: DeclineMatchUseCase,
    private val chatRepository: ChatRepository,
) {
    suspend operator fun invoke(id: Int): UseCaseResult<Unit> {
        val result = declineMatchUseCase(id)
        if (result is UseCaseResult.Ok) {
            chatRepository.removeChatProfile(id)
        }
        return result
    }
}
