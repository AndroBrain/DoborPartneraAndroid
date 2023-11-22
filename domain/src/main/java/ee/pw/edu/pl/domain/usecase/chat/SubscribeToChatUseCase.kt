package ee.pw.edu.pl.domain.usecase.chat

import ee.pw.edu.pl.domain.repository.ChatRepository

class SubscribeToChatUseCase(private val chatRepository: ChatRepository) {
    suspend operator fun invoke() {
        chatRepository.subscribeToChat()
    }
}
