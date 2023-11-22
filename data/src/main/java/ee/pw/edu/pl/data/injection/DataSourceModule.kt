package ee.pw.edu.pl.data.injection

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ee.pw.edu.pl.data.core.remote.ApiService
import ee.pw.edu.pl.data.datasource.account.remote.AccountRemoteDataSource
import ee.pw.edu.pl.data.datasource.account.remote.RetrofitAccountRemoteDataSource
import ee.pw.edu.pl.data.datasource.auth.local.AuthLocalDataSource
import ee.pw.edu.pl.data.datasource.auth.local.PrefsAuthLocalDataSource
import ee.pw.edu.pl.data.datasource.auth.remote.AuthRemoteDataSource
import ee.pw.edu.pl.data.datasource.auth.remote.RetrofitAuthRemoteDataSource
import ee.pw.edu.pl.data.datasource.chat.remote.ChatRemoteDataSource
import ee.pw.edu.pl.data.datasource.chat.remote.SignalRChatRemoteDataSource
import ee.pw.edu.pl.data.datasource.images.remote.FirebaseImageRemoteDataSource
import ee.pw.edu.pl.data.datasource.images.remote.ImageRemoteDataSource
import ee.pw.edu.pl.data.datasource.match.remote.MatchRemoteDataSource
import ee.pw.edu.pl.data.datasource.match.remote.RetrofitMatchRemoteDataSource
import ee.pw.edu.pl.data.datasource.message.local.MessageDao
import ee.pw.edu.pl.data.datasource.message.local.MessageLocalDataSource
import ee.pw.edu.pl.data.datasource.message.local.RoomMessageLocalDataSource
import ee.pw.edu.pl.data.datasource.message.remote.MessageRemoteDataSource
import ee.pw.edu.pl.data.datasource.message.remote.RetrofitMessageRemoteDataSource
import ee.pw.edu.pl.data.datasource.profile.local.ProfileDao
import ee.pw.edu.pl.data.datasource.profile.local.ProfileLocalDataSource
import ee.pw.edu.pl.data.datasource.profile.local.RoomProfileLocalDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun provideAuthRemoteDataSource(
        apiService: ApiService,
    ): AuthRemoteDataSource = RetrofitAuthRemoteDataSource(apiService)

    @Provides
    @Singleton
    fun provideAuthLocalDataSource(
        prefs: SharedPreferences,
    ): AuthLocalDataSource = PrefsAuthLocalDataSource(prefs)

    @Provides
    fun provideImageRemoteDataSource(
        firebaseStorage: FirebaseStorage,
    ): ImageRemoteDataSource = FirebaseImageRemoteDataSource(firebaseStorage)

    @Provides
    fun provideAccountRemoteDataSource(
        apiService: ApiService,
    ): AccountRemoteDataSource = RetrofitAccountRemoteDataSource(apiService)

    @Provides
    fun provideMatchRemoteDataSource(
        apiService: ApiService,
    ): MatchRemoteDataSource = RetrofitMatchRemoteDataSource(apiService)

    @Provides
    @Singleton
    fun provideChatRemoteDataSource(
        @ApplicationContext context: Context,
        authLocalDataSource: AuthLocalDataSource,
    ): ChatRemoteDataSource = SignalRChatRemoteDataSource(context, authLocalDataSource)

    @Provides
    fun provideMessageLocalDataSource(messageDao: MessageDao): MessageLocalDataSource =
        RoomMessageLocalDataSource(messageDao)

    @Provides
    fun provideProfileLocalDataSource(profileDao: ProfileDao): ProfileLocalDataSource =
        RoomProfileLocalDataSource(profileDao)

    @Provides
    fun provideMessageRemoteDataSource(api: ApiService): MessageRemoteDataSource =
        RetrofitMessageRemoteDataSource(api)
}
