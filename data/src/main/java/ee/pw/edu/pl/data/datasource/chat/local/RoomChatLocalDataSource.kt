package ee.pw.edu.pl.data.datasource.chat.local

import ee.pw.edu.pl.data.model.chat.local.MessageEntity
import ee.pw.edu.pl.data.model.chat.local.ProfileWithMessages
import kotlinx.coroutines.flow.Flow

class RoomChatLocalDataSource(
    private val chatDao: ChatDao,
) : ChatLocalDataSource {
    override fun getProfilesWithMessages(): Flow<List<ProfileWithMessages>> =
        chatDao.getProfilesWithMessages()

    override fun getMessages(ownerId: Int): Flow<List<MessageEntity>> = chatDao.getMessages(ownerId)

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
