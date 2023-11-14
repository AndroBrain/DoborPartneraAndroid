package ee.pw.edu.pl.data.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ee.pw.edu.pl.data.datasource.chat.local.ChatProfileDao
import ee.pw.edu.pl.data.model.chat.local.ChatProfileEntity
import ee.pw.edu.pl.data.model.chat.local.MessageEntity

@Database(
    entities = [
        ChatProfileEntity::class,
        MessageEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatProfileDao(): ChatProfileDao
}
