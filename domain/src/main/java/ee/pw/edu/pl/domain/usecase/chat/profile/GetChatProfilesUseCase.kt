package ee.pw.edu.pl.domain.usecase.chat.profile

import ee.pw.edu.pl.domain.repository.MessageRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetChatProfilesUseCase @Inject constructor(
    private val messageRepository: MessageRepository,
) {
    operator fun invoke(): Flow<List<ChatProfile>> = messageRepository.getProfileChats()
}
