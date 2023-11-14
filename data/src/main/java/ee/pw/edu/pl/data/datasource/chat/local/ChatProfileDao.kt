package ee.pw.edu.pl.data.datasource.chat.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ee.pw.edu.pl.data.model.chat.local.ChatProfileWithMessages
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatProfileDao {
    @Transaction
    @Query("SELECT * FROM ChatProfileEntity")
    suspend fun getProfilesWithMessages(): Flow<List<ChatProfileWithMessages>>
}
