package ee.pw.edu.pl.data.injection

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ee.pw.edu.pl.data.R
import ee.pw.edu.pl.data.core.local.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            context.getString(R.string.database_name)
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideChatProfileDao(database: AppDatabase) = database.chatProfileDao()
}