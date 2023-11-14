package ee.pw.edu.pl.domain.usecase.chat.profile

import ee.pw.edu.pl.domain.repository.ChatRepository
import javax.inject.Inject

class UpdateChatProfilesUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) {
    suspend operator fun invoke() = chatRepository.updateChatProfiles()
}
