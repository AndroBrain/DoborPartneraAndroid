package ee.pw.edu.pl.domain.usecase.chat

import ee.pw.edu.pl.domain.repository.ChatRepository
import javax.inject.Inject

class SubscribeToChatUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke() {
        chatRepository.subscribeToChat()
    }
}
