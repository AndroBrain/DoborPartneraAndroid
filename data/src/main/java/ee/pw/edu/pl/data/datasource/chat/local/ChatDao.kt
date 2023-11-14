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

    @Query("SELECT * FROM MessageEntity WHERE ownerId == :ownerId")
    fun getMessages(ownerId: Int): Flow<List<MessageEntity>>
}
