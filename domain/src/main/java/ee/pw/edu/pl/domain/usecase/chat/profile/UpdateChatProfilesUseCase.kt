package ee.pw.edu.pl.domain.usecase.chat.profile

import ee.pw.edu.pl.domain.repository.MessageRepository
import javax.inject.Inject

class UpdateChatProfilesUseCase @Inject constructor(
    private val messageRepository: MessageRepository,
) {
    suspend operator fun invoke() = messageRepository.updateChatProfiles()
}
