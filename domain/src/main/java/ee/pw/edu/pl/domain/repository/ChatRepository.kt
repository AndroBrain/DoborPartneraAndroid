package ee.pw.edu.pl.domain.repository

import ee.pw.edu.pl.domain.usecase.chat.SendMessageForm

interface ChatRepository {
    suspend fun subscribeToChat()
    fun sendMessage(form: SendMessageForm)
}
