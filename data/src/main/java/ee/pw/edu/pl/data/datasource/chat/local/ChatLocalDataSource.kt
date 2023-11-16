package ee.pw.edu.pl.data.datasource.chat.local

import ee.pw.edu.pl.data.model.chat.local.MessageEntity
import ee.pw.edu.pl.data.model.chat.local.ProfileWithMessages
import ee.pw.edu.pl.data.model.profile.local.ProfileEntity
import kotlinx.coroutines.flow.Flow

interface ChatLocalDataSource {
    fun getProfilesWithMessages(): Flow<List<ProfileWithMessages>>
    fun getMessages(ownerId: Int): Flow<List<MessageEntity>>
    suspend fun insertChatProfiles(profiles: List<ProfileEntity>)
    suspend fun removeChatPerson(id: Int)
    suspend fun removeAll()
    suspend fun insertMessages(messageEntities: List<MessageEntity>)
}
