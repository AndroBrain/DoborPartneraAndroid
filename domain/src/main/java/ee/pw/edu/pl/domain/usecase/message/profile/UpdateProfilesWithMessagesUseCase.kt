package ee.pw.edu.pl.domain.usecase.message.profile

import ee.pw.edu.pl.domain.repository.MessageRepository
import javax.inject.Inject

class UpdateProfilesWithMessagesUseCase @Inject constructor(
    private val messageRepository: MessageRepository,
) {
    suspend operator fun invoke() = messageRepository.updateProfilesWithMessages()
}
