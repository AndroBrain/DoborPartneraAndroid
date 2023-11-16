package ee.pw.edu.pl.data.injection

import android.content.SharedPreferences
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ee.pw.edu.pl.data.core.remote.ApiService
import ee.pw.edu.pl.data.datasource.auth.local.AuthLocalDataSource
import ee.pw.edu.pl.data.datasource.auth.local.PrefsAuthLocalDataSource
import ee.pw.edu.pl.data.datasource.auth.remote.AuthRemoteDataSource
import ee.pw.edu.pl.data.datasource.auth.remote.RetrofitAuthRemoteDataSource
import ee.pw.edu.pl.data.datasource.chat.local.ChatDao
import ee.pw.edu.pl.data.datasource.chat.local.ChatLocalDataSource
import ee.pw.edu.pl.data.datasource.chat.local.RoomChatLocalDataSource
import ee.pw.edu.pl.data.datasource.chat.remote.ChatRemoteDataSource
import ee.pw.edu.pl.data.datasource.chat.remote.SignalRChatRemoteDataSource
import ee.pw.edu.pl.data.datasource.images.remote.FirebaseImageRemoteDataSource
import ee.pw.edu.pl.data.datasource.images.remote.ImageRemoteDataSource
import ee.pw.edu.pl.data.datasource.match.remote.MatchRemoteDataSource
import ee.pw.edu.pl.data.datasource.match.remote.RetrofitMatchRemoteDataSource
import ee.pw.edu.pl.data.datasource.profile.local.ProfileDao
import ee.pw.edu.pl.data.datasource.profile.local.ProfileLocalDataSource
import ee.pw.edu.pl.data.datasource.profile.local.RoomProfileLocalDataSource
import ee.pw.edu.pl.data.datasource.profile.remote.ProfileRemoteDataSource
import ee.pw.edu.pl.data.datasource.profile.remote.RetrofitProfileRemoteDataSource
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
    fun provideProfileRemoteDataSource(
        apiService: ApiService,
    ): ProfileRemoteDataSource = RetrofitProfileRemoteDataSource(apiService)

    @Provides
    fun provideMatchRemoteDataSource(
        apiService: ApiService,
    ): MatchRemoteDataSource = RetrofitMatchRemoteDataSource(apiService)

    @Provides
    @Singleton
    fun provideChatRemoteDataSource(
        authLocalDataSource: AuthLocalDataSource,
        api: ApiService
    ): ChatRemoteDataSource =
        SignalRChatRemoteDataSource(authLocalDataSource, api)

    @Provides
    fun provideChatLocalDataSource(chatDao: ChatDao): ChatLocalDataSource =
        RoomChatLocalDataSource(chatDao)

    @Provides
    fun provideProfileLocalDataSource(profileDao: ProfileDao): ProfileLocalDataSource =
        RoomProfileLocalDataSource(profileDao)
}
