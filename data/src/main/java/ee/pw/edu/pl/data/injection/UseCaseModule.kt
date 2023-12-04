package ee.pw.edu.pl.data.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ee.pw.edu.pl.domain.repository.AccountRepository
import ee.pw.edu.pl.domain.repository.AuthRepository
import ee.pw.edu.pl.domain.repository.ChatRepository
import ee.pw.edu.pl.domain.repository.MatchRepository
import ee.pw.edu.pl.domain.repository.MessageRepository
import ee.pw.edu.pl.domain.repository.ProfileRepository
import ee.pw.edu.pl.domain.usecase.account.EditAccountUseCase
import ee.pw.edu.pl.domain.usecase.account.GetAccountUseCase
import ee.pw.edu.pl.domain.usecase.account.GetIsAccountFilledUseCase
import ee.pw.edu.pl.domain.usecase.account.SetTestUseCase
import ee.pw.edu.pl.domain.usecase.auth.login.IsLoggedInUseCase
import ee.pw.edu.pl.domain.usecase.auth.login.LoginUseCase
import ee.pw.edu.pl.domain.usecase.auth.register.RegisterUseCase
import ee.pw.edu.pl.domain.usecase.chat.SendMessageUseCase
import ee.pw.edu.pl.domain.usecase.chat.SubscribeToChatUseCase
import ee.pw.edu.pl.domain.usecase.match.DeclineMatchUseCase
import ee.pw.edu.pl.domain.usecase.match.GetMatchProfileUseCase
import ee.pw.edu.pl.domain.usecase.match.GetMatchesUseCase
import ee.pw.edu.pl.domain.usecase.message.GetMessagesUseCase
import ee.pw.edu.pl.domain.usecase.message.LoadMoreMessagesUseCase
import ee.pw.edu.pl.domain.usecase.message.profile.GetProfilesWithMessagesUseCase
import ee.pw.edu.pl.domain.usecase.message.profile.RemoveProfileWithMessagesUseCase
import ee.pw.edu.pl.domain.usecase.message.profile.UpdateProfilesWithMessagesUseCase

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideEditAccountUseCase(accountRepository: AccountRepository) =
        EditAccountUseCase(accountRepository)

    @Provides
    fun provideGetAccountUseCase(accountRepository: AccountRepository) =
        GetAccountUseCase(accountRepository)

    @Provides
    fun provideGetIsAccountFilledUseCase() = GetIsAccountFilledUseCase()

    @Provides
    fun provideIsLoggedInUseCase(authRepository: AuthRepository) = IsLoggedInUseCase(authRepository)

    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository) = LoginUseCase(authRepository)

    @Provides
    fun provideRegisterUseCase(authRepository: AuthRepository) = RegisterUseCase(authRepository)

    @Provides
    fun provideSendMessageUseCase(chatRepository: ChatRepository) =
        SendMessageUseCase(chatRepository)

    @Provides
    fun provideSubscribeToChatUseCase(chatRepository: ChatRepository) =
        SubscribeToChatUseCase(chatRepository)

    @Provides
    fun provideDeclineMatchUseCase(matchRepository: MatchRepository) =
        DeclineMatchUseCase(matchRepository)

    @Provides
    fun provideGetMatchProfileUseCase(matchRepository: MatchRepository) =
        GetMatchProfileUseCase(matchRepository)

    @Provides
    fun provideGetMatchesUseCase(matchRepository: MatchRepository) =
        GetMatchesUseCase(matchRepository)

    @Provides
    fun provideGetMessagesUseCase(messageRepository: MessageRepository) =
        GetMessagesUseCase(messageRepository)

    @Provides
    fun provideLoadMoreMessagesUseCase(messageRepository: MessageRepository) =
        LoadMoreMessagesUseCase(messageRepository)

    @Provides
    fun provideGetProfilesWithMessagesUseCase(messageRepository: MessageRepository) =
        GetProfilesWithMessagesUseCase(messageRepository)

    @Provides
    fun provideRemoveProfileWithMessagesUseCase(
        declineMatchUseCase: DeclineMatchUseCase,
        profileRepository: ProfileRepository,
    ) = RemoveProfileWithMessagesUseCase(declineMatchUseCase, profileRepository)

    @Provides
    fun provideUpdateProfilesWithMessagesUseCase(messageRepository: MessageRepository) =
        UpdateProfilesWithMessagesUseCase(messageRepository)

    @Provides
    fun provideSetTestUseCase(accountRepository: AccountRepository) =
        SetTestUseCase(accountRepository)
}
