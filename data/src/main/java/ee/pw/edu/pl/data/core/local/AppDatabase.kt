package ee.pw.edu.pl.data.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ee.pw.edu.pl.data.datasource.chat.local.ChatDao
import ee.pw.edu.pl.data.model.chat.local.MessageEntity
import ee.pw.edu.pl.data.model.profile.local.ProfileEntity

@Database(
    entities = [
        ProfileEntity::class,
        MessageEntity::class,
    ],
    version = 3,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
}
