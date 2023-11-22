package ee.pw.edu.pl.domain.usecase.message.profile

import ee.pw.edu.pl.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow

class GetProfilesWithMessagesUseCase(private val messageRepository: MessageRepository) {
    operator fun invoke(): Flow<List<ProfileWithMessages>> =
        messageRepository.getProfilesWithMessages()
}
