package ee.pw.edu.pl.domain.usecase.message

import ee.pw.edu.pl.domain.repository.MessageRepository
import javax.inject.Inject

class LoadMoreMessagesUseCase @Inject constructor(
    private val messageRepository: MessageRepository,
) {
    suspend operator fun invoke(id: Int) = messageRepository.loadMoreMessages(id)
}
