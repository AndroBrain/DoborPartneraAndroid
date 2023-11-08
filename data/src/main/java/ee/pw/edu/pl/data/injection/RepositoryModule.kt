package ee.pw.edu.pl.data.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ee.pw.edu.pl.data.datasource.auth.AuthLocalDataSource
import ee.pw.edu.pl.data.datasource.auth.AuthRemoteDataSource
import ee.pw.edu.pl.data.datasource.images.ImageRemoteDataSource
import ee.pw.edu.pl.data.repository.ProfileRepositoryImpl
import ee.pw.edu.pl.data.repository.auth.AuthRepositoryImpl
import ee.pw.edu.pl.domain.repository.ProfileRepository
import ee.pw.edu.pl.domain.usecase.auth.AuthRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAuthRepository(
        authRemoteDataSource: AuthRemoteDataSource,
        authLocalDataSource: AuthLocalDataSource,
    ): AuthRepository = AuthRepositoryImpl(
        authRemoteDataSource = authRemoteDataSource,
        authLocalDataSource = authLocalDataSource,
    )

    @Provides
    fun provideProfileRepository(
        imageRemoteDataSource: ImageRemoteDataSource,
    ): ProfileRepository = ProfileRepositoryImpl(
        imageRemoteDataSource = imageRemoteDataSource,
    )
}
