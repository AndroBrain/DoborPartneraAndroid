package ee.pw.edu.pl.domain.repository

import ee.pw.edu.pl.domain.usecase.chat.SendMessageForm
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getChat(): Flow<String>
    fun sendMessage(form: SendMessageForm)
}