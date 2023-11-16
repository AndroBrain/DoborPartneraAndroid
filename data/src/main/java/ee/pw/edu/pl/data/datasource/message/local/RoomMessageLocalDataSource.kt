package ee.pw.edu.pl.data.datasource.message.local

import ee.pw.edu.pl.data.model.message.local.MessageEntity
import ee.pw.edu.pl.data.model.message.local.ProfileWithMessages
import kotlinx.coroutines.flow.Flow

class RoomMessageLocalDataSource(
    private val messageDao: MessageDao,
) : MessageLocalDataSource {
    override fun getProfilesWithMessages(): Flow<List<ProfileWithMessages>> =
        messageDao.getProfilesWithMessages()

    override fun get(ownerId: Int): Flow<List<MessageEntity>> =
        messageDao.getMessages(ownerId)

    override suspend fun removeAll() {
        messageDao.removeAll()
    }

    override suspend fun insert(messageEntities: List<MessageEntity>) {
        messageEntities.forEach { message ->
            messageDao.insertMessage(message)
        }
    }
}
