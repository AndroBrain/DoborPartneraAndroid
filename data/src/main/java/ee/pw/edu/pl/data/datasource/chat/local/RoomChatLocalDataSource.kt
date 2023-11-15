package ee.pw.edu.pl.data.datasource.chat.local

import ee.pw.edu.pl.data.model.chat.local.ChatProfileEntity
import ee.pw.edu.pl.data.model.chat.local.ChatProfileWithMessages
import ee.pw.edu.pl.data.model.chat.local.MessageEntity
import kotlinx.coroutines.flow.Flow

class RoomChatLocalDataSource(
    private val chatDao: ChatDao,
) : ChatLocalDataSource {
    override fun getProfilesWithMessages(): Flow<List<ChatProfileWithMessages>> =
        chatDao.getProfilesWithMessages()

    override fun getMessages(ownerId: Int): Flow<List<MessageEntity>> = chatDao.getMessages(ownerId)
    override suspend fun insertChatProfiles(profiles: List<ChatProfileEntity>) {
        profiles.forEach { profile ->
            chatDao.insertChatProfile(profile)
        }
    }

    override suspend fun removeChatPerson(id: Int) {
        chatDao.remove(id)
    }

    override suspend fun removeAll() {
        chatDao.removeAllMessages()
        chatDao.removeAllProfiles()
    }

    override suspend fun insertMessages(messageEntities: List<MessageEntity>) {
        messageEntities.forEach { message ->
            chatDao.insertMessage(message)
        }
    }
}
