package ee.pw.edu.pl.data.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ee.pw.edu.pl.data.datasource.auth.AuthLocalDataSource
import ee.pw.edu.pl.data.datasource.auth.AuthRemoteDataSource
import ee.pw.edu.pl.data.datasource.chat.remote.ChatRemoteDataSource
import ee.pw.edu.pl.data.datasource.images.ImageRemoteDataSource
import ee.pw.edu.pl.data.datasource.match.MatchRemoteDataSource
import ee.pw.edu.pl.data.datasource.profile.ProfileRemoteDataSource
import ee.pw.edu.pl.data.repository.AuthRepositoryImpl
import ee.pw.edu.pl.data.repository.ChatRepositoryImpl
import ee.pw.edu.pl.data.repository.MatchRepositoryImpl
import ee.pw.edu.pl.data.repository.ProfileRepositoryImpl
import ee.pw.edu.pl.domain.repository.AuthRepository
import ee.pw.edu.pl.domain.repository.ChatRepository
import ee.pw.edu.pl.domain.repository.MatchRepository
import ee.pw.edu.pl.domain.repository.ProfileRepository

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
        profileRemoteDataSource: ProfileRemoteDataSource,
    ): ProfileRepository = ProfileRepositoryImpl(
        imageRemoteDataSource = imageRemoteDataSource,
        profileRemoteDataSource = profileRemoteDataSource,
    )

    @Provides
    fun provideMatchRepository(
        matchRemoteDataSource: MatchRemoteDataSource,
    ): MatchRepository = MatchRepositoryImpl(
        matchRemoteDataSource = matchRemoteDataSource,
    )

    @Provides
    fun provideChatRepository(
        chatRemoteDataSource: ChatRemoteDataSource
    ): ChatRepository = ChatRepositoryImpl(chatRemoteDataSource = chatRemoteDataSource)
}
