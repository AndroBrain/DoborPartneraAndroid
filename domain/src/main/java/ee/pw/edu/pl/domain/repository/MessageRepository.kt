package ee.pw.edu.pl.domain.repository

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.usecase.chat.Chat
import ee.pw.edu.pl.domain.usecase.chat.profile.ChatProfile
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getMessages(id: Int): Flow<List<Chat>>
    fun getProfileChats(): Flow<List<ChatProfile>>
    suspend fun updateChatProfiles(): UseCaseResult<Unit>
    suspend fun removeChatProfile(id: Int)
}
