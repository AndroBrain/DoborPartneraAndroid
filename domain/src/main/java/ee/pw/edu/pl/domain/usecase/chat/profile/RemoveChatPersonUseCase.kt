package ee.pw.edu.pl.domain.usecase.chat.profile

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.MessageRepository
import ee.pw.edu.pl.domain.usecase.match.DeclineMatchUseCase
import javax.inject.Inject

class RemoveChatPersonUseCase @Inject constructor(
    private val declineMatchUseCase: DeclineMatchUseCase,
    private val messageRepository: MessageRepository,
) {
    suspend operator fun invoke(id: Int): UseCaseResult<Unit> {
        val result = declineMatchUseCase(id)
        if (result is UseCaseResult.Ok) {
            messageRepository.removeChatProfile(id)
        }
        return result
    }
}
