package ee.pw.edu.pl.domain.repository

import ee.pw.edu.pl.domain.usecase.chat.SendMessageForm
import ee.pw.edu.pl.domain.usecase.chat.profile.ChatProfile
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getChat(): Flow<String>
    fun sendMessage(form: SendMessageForm)

    fun getProfileChats(): Flow<List<ChatProfile>>
}
