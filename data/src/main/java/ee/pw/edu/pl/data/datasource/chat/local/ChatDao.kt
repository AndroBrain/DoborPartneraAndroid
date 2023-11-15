package ee.pw.edu.pl.data.datasource.chat.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ee.pw.edu.pl.data.model.chat.local.ChatProfileEntity
import ee.pw.edu.pl.data.model.chat.local.ChatProfileWithMessages
import ee.pw.edu.pl.data.model.chat.local.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChatProfile(entity: ChatProfileEntity)

    @Transaction
    @Query("SELECT * FROM ChatProfileEntity")
    fun getProfilesWithMessages(): Flow<List<ChatProfileWithMessages>>

    @Query("SELECT * FROM MessageEntity WHERE fromUser == :chatId OR toUser == :chatId")
    fun getMessages(chatId: Int): Flow<List<MessageEntity>>

    @Query("DELETE FROM ChatProfileEntity WHERE id == :id")
    suspend fun remove(id: Int)

    @Query("DELETE FROM MessageEntity")
    suspend fun removeAllMessages()

    @Query("DELETE FROM ChatProfileEntity")
    suspend fun removeAllProfiles()

    @Insert
    suspend fun insertMessage(message: MessageEntity)
}
