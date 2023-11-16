package ee.pw.edu.pl.data.datasource.chat.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ee.pw.edu.pl.data.model.chat.local.MessageEntity
import ee.pw.edu.pl.data.model.chat.local.ProfileWithMessages
import ee.pw.edu.pl.data.model.profile.local.ProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChatProfile(entity: ProfileEntity)

    @Transaction
    @Query("SELECT * FROM ProfileEntity")
    fun getProfilesWithMessages(): Flow<List<ProfileWithMessages>>

    @Query("SELECT * FROM MessageEntity WHERE fromUser == :chatId OR toUser == :chatId ORDER BY timestamp")
    fun getMessages(chatId: Int): Flow<List<MessageEntity>>

    @Query("DELETE FROM ProfileEntity WHERE id == :id")
    suspend fun remove(id: Int)

    @Query("DELETE FROM MessageEntity")
    suspend fun removeAllMessages()

    @Query("DELETE FROM ProfileEntity")
    suspend fun removeAllProfiles()

    @Insert
    suspend fun insertMessage(message: MessageEntity)
}
