package ee.pw.edu.pl.domain.usecase.chat

import ee.pw.edu.pl.domain.repository.ChatRepository

class SendMessageUseCase(private val chatRepository: ChatRepository) {
    operator fun invoke(form: SendMessageForm) {
        chatRepository.sendMessage(form)
    }
}
