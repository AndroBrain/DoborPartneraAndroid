package ee.pw.edu.pl.domain.usecase.message.profile

import ee.pw.edu.pl.domain.repository.MessageRepository

class UpdateProfilesWithMessagesUseCase(private val messageRepository: MessageRepository) {
    suspend operator fun invoke() = messageRepository.updateProfilesWithMessages()
}
