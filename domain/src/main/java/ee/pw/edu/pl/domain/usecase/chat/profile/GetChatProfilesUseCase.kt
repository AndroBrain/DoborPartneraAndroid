package ee.pw.edu.pl.domain.usecase.chat.profile

import ee.pw.edu.pl.domain.repository.ChatRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetChatProfilesUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) {
    operator fun invoke(): Flow<List<ChatProfile>> = chatRepository.getProfileChats()
}
