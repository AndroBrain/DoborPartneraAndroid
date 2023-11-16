package ee.pw.edu.pl.domain.usecase.chat

import ee.pw.edu.pl.domain.repository.MessageRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetChatMessagesUseCase @Inject constructor(
    private val messageRepository: MessageRepository,
) {
    operator fun invoke(id: Int): Flow<List<Chat>> = messageRepository.getMessages(id)
}
