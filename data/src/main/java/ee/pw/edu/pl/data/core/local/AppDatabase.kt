package ee.pw.edu.pl.data.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ee.pw.edu.pl.data.datasource.message.local.MessageDao
import ee.pw.edu.pl.data.datasource.profile.local.ProfileDao
import ee.pw.edu.pl.data.model.message.local.MessageEntity
import ee.pw.edu.pl.data.model.profile.local.ProfileEntity

@Database(
    entities = [
        ProfileEntity::class,
        MessageEntity::class,
    ],
    version = 4,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatDao(): MessageDao
    abstract fun profileDao(): ProfileDao
}
