package ee.pw.edu.pl.data.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ee.pw.edu.pl.data.datasource.auth.local.AuthLocalDataSource
import ee.pw.edu.pl.data.datasource.auth.remote.AuthRemoteDataSource
import ee.pw.edu.pl.data.datasource.chat.remote.ChatRemoteDataSource
import ee.pw.edu.pl.data.datasource.images.remote.ImageRemoteDataSource
import ee.pw.edu.pl.data.datasource.match.remote.MatchRemoteDataSource
import ee.pw.edu.pl.data.datasource.message.local.MessageLocalDataSource
import ee.pw.edu.pl.data.datasource.message.remote.MessageRemoteDataSource
import ee.pw.edu.pl.data.datasource.profile.local.ProfileLocalDataSource
import ee.pw.edu.pl.data.datasource.profile.remote.ProfileRemoteDataSource
import ee.pw.edu.pl.data.repository.AuthRepositoryImpl
import ee.pw.edu.pl.data.repository.ChatRepositoryImpl
import ee.pw.edu.pl.data.repository.MatchRepositoryImpl
import ee.pw.edu.pl.data.repository.MessageRepositoryImpl
import ee.pw.edu.pl.data.repository.ProfileRepositoryImpl
import ee.pw.edu.pl.domain.repository.AuthRepository
import ee.pw.edu.pl.domain.repository.ChatRepository
import ee.pw.edu.pl.domain.repository.MatchRepository
import ee.pw.edu.pl.domain.repository.MessageRepository
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
        chatRemoteDataSource: ChatRemoteDataSource,
        messageLocalDataSource: MessageLocalDataSource,
    ): ChatRepository = ChatRepositoryImpl(
        chatRemoteDataSource = chatRemoteDataSource,
        messageLocalDataSource = messageLocalDataSource,
    )

    @Provides
    fun provideMessageRepository(
        messageRemoteDataSource: MessageRemoteDataSource,
        messageLocalDataSource: MessageLocalDataSource,
        profileLocalDataSource: ProfileLocalDataSource,
    ): MessageRepository = MessageRepositoryImpl(
        messageRemoteDataSource = messageRemoteDataSource,
        messageLocalDataSource = messageLocalDataSource,
        profileLocalDataSource = profileLocalDataSource,
    )
}
