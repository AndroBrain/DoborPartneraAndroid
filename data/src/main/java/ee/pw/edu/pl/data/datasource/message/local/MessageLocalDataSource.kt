package ee.pw.edu.pl.data.datasource.message.local

import ee.pw.edu.pl.data.model.message.local.MessageEntity
import ee.pw.edu.pl.data.model.message.local.ProfileWithMessages
import kotlinx.coroutines.flow.Flow

interface MessageLocalDataSource {
    fun getProfilesWithMessages(): Flow<List<ProfileWithMessages>>
    fun get(ownerId: Int): Flow<List<MessageEntity>>
    suspend fun removeAll()
    suspend fun insert(messageEntities: List<MessageEntity>)
}
