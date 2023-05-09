package ee.pw.edu.pl.doborpartnera.ui.screen.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.domain.usecase.chat.GetChatsUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class ChatViewModel @Inject constructor(
    getChatsUseCase: GetChatsUseCase,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<ChatState>(savedStateHandle, ChatState()) {
    init {
        viewModelScope.launch {
            getChatsUseCase().onEach { chats ->
                updateState { state -> state.copy(chats = chats) }
            }.launchIn(this)
        }
    }
}
