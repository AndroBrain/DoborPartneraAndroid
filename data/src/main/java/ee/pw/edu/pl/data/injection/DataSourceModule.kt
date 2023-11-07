package ee.pw.edu.pl.data.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ee.pw.edu.pl.data.core.ApiService
import ee.pw.edu.pl.data.datasource.auth.AuthRemoteDataSource
import ee.pw.edu.pl.data.datasource.auth.RetrofitAuthRemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun provideAuthRemoteDataSource(
        apiService: ApiService,
    ): AuthRemoteDataSource = RetrofitAuthRemoteDataSource(apiService)
}
