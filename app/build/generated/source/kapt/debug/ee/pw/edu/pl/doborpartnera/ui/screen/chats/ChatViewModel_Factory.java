package ee.pw.edu.pl.doborpartnera.ui.screen.chats;

import androidx.lifecycle.SavedStateHandle;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ee.pw.edu.pl.domain.usecase.chat.GetChatsUseCase;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ChatViewModel_Factory implements Factory<ChatsViewModel> {
  private final Provider<GetChatsUseCase> getChatsUseCaseProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public ChatViewModel_Factory(Provider<GetChatsUseCase> getChatsUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.getChatsUseCaseProvider = getChatsUseCaseProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public ChatsViewModel get() {
    return newInstance(getChatsUseCaseProvider.get(), savedStateHandleProvider.get());
  }

  public static ChatViewModel_Factory create(Provider<GetChatsUseCase> getChatsUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new ChatViewModel_Factory(getChatsUseCaseProvider, savedStateHandleProvider);
  }

  public static ChatsViewModel newInstance(GetChatsUseCase getChatsUseCase,
                                           SavedStateHandle savedStateHandle) {
    return new ChatsViewModel(getChatsUseCase, savedStateHandle);
  }
}
