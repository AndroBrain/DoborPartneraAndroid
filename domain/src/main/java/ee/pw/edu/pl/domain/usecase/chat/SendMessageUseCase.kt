package ee.pw.edu.pl.domain.usecase.chat

import ee.pw.edu.pl.domain.repository.ChatRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    operator fun invoke(form: SendMessageForm) {
        chatRepository.sendMessage(form)
    }
}
