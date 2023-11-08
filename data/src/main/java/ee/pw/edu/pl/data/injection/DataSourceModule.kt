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
import ee.pw.edu.pl.data.datasource.images.FirebaseImageRemoteDataSource
import ee.pw.edu.pl.data.datasource.images.ImageRemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun provideAuthRemoteDataSource(
        apiService: ApiService,
    ): AuthRemoteDataSource = RetrofitAuthRemoteDataSource(apiService)

    @Provides
    fun provideAuthLocalDataSource(
        prefs: SharedPreferences,
    ): AuthLocalDataSource = PrefsAuthLocalDataSource(prefs)

    @Provides
    fun provideImageRemoteDataSource(
        firebaseStorage: FirebaseStorage,
    ): ImageRemoteDataSource = FirebaseImageRemoteDataSource(firebaseStorage)
}
