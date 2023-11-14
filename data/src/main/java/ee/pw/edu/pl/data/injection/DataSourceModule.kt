package ee.pw.edu.pl.data.injection

import android.content.SharedPreferences
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ee.pw.edu.pl.data.core.remote.ApiService
import ee.pw.edu.pl.data.datasource.auth.AuthLocalDataSource
import ee.pw.edu.pl.data.datasource.auth.AuthRemoteDataSource
import ee.pw.edu.pl.data.datasource.auth.PrefsAuthLocalDataSource
import ee.pw.edu.pl.data.datasource.auth.RetrofitAuthRemoteDataSource
import ee.pw.edu.pl.data.datasource.chat.ChatRemoteDataSource
import ee.pw.edu.pl.data.datasource.chat.SignalRChatRemoteDataSource
import ee.pw.edu.pl.data.datasource.images.FirebaseImageRemoteDataSource
import ee.pw.edu.pl.data.datasource.images.ImageRemoteDataSource
import ee.pw.edu.pl.data.datasource.match.MatchRemoteDataSource
import ee.pw.edu.pl.data.datasource.match.RetrofitMatchRemoteDataSource
import ee.pw.edu.pl.data.datasource.profile.ProfileRemoteDataSource
import ee.pw.edu.pl.data.datasource.profile.RetrofitProfileRemoteDataSource
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
    fun provideChatRemoteDataSource(): ChatRemoteDataSource =
        SignalRChatRemoteDataSource()
}
