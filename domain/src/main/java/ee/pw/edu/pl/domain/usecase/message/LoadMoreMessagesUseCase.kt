package ee.pw.edu.pl.domain.usecase.message

import ee.pw.edu.pl.domain.repository.MessageRepository

class LoadMoreMessagesUseCase(private val messageRepository: MessageRepository) {
    suspend operator fun invoke(id: Int) = messageRepository.loadMoreMessages(id)
}
