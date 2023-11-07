package ee.pw.edu.pl.data.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ee.pw.edu.pl.data.datasource.auth.AuthRemoteDataSource
import ee.pw.edu.pl.data.repository.auth.AuthRepositoryImpl
import ee.pw.edu.pl.domain.usecase.auth.AuthRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAuthRepository(
        authRemoteDataSource: AuthRemoteDataSource,
    ): AuthRepository = AuthRepositoryImpl(authRemoteDataSource)
}
