package ee.pw.edu.pl.data.datasource.profile.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ee.pw.edu.pl.data.model.profile.local.ProfileEntity

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ProfileEntity)

    @Query("DELETE FROM ProfileEntity WHERE id == :id")
    suspend fun remove(id: Int)

    @Query("DELETE FROM ProfileEntity")
    suspend fun removeAll()
}
