package ee.pw.edu.pl.data.datasource.message.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ee.pw.edu.pl.data.model.message.local.MessageEntity
import ee.pw.edu.pl.data.model.message.local.ProfileWithMessagesRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Transaction
    @Query("SELECT * FROM ProfileEntity")
    fun getProfilesWithMessages(): Flow<List<ProfileWithMessagesRelation>>

    @Query("SELECT * FROM MessageEntity WHERE fromUser == :chatId OR toUser == :chatId ORDER BY timestamp")
    fun getMessages(chatId: Int): Flow<List<MessageEntity>>

    @Query("DELETE FROM MessageEntity")
    suspend fun removeAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)
}
