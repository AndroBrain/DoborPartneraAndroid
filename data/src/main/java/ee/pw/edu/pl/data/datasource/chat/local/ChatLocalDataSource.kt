package ee.pw.edu.pl.data.datasource.chat.local

import ee.pw.edu.pl.data.model.chat.local.ChatProfileEntity
import ee.pw.edu.pl.data.model.chat.local.ChatProfileWithMessages
import ee.pw.edu.pl.data.model.chat.local.MessageEntity
import kotlinx.coroutines.flow.Flow

interface ChatLocalDataSource {
    fun getProfilesWithMessages(): Flow<List<ChatProfileWithMessages>>
    fun getMessages(ownerId: Int): Flow<List<MessageEntity>>
    suspend fun insertChatProfiles(profiles: List<ChatProfileEntity>)
    suspend fun removeChatPerson(id: Int)
}
