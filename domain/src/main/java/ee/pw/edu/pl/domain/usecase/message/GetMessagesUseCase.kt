package ee.pw.edu.pl.domain.usecase.message

import ee.pw.edu.pl.domain.repository.MessageRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetMessagesUseCase @Inject constructor(
    private val messageRepository: MessageRepository,
) {
    operator fun invoke(id: Int): Flow<List<Message>> = messageRepository.getMessages(id)
}
