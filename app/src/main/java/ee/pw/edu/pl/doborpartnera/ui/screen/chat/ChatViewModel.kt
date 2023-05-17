package ee.pw.edu.pl.doborpartnera.ui.screen.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.result.getMessage
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.domain.core.result.fold
import ee.pw.edu.pl.domain.usecase.chat.Chat
import ee.pw.edu.pl.domain.usecase.chat.SubscribeToChatUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val subscribeToChatUseCase: SubscribeToChatUseCase,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<ChatState>(savedStateHandle, ChatState()) {
    private val args = ChatArgs(savedStateHandle)

    init {
        updateState { state -> state.copy(name = args.name, imageUrl = args.imageUrl) }
        subscribeToChat()
    }

    fun clearErrorMsg() {
        updateState { state -> state.copy(errorMsg = null) }
    }

    fun updateMessage(message: String) {
        updateState { state -> state.copy(message = message) }
    }

    fun sendMessage() {
        updateState { state ->
            state.copy(
                chats = state.chats + Chat(text = state.message, isYour = true),
                message = "",
            )
        }
    }

    private fun subscribeToChat() {
        viewModelScope.launch {
            subscribeToChatUseCase().onEach { result ->
                result.fold(
                    onOk = {
                        updateState { state -> state.copy(chats = state.chats + it.value) }
                    }, onError = { error ->
                        updateState { state -> state.copy(errorMsg = error.type.getMessage()) }
                    }
                )
            }.launchIn(this)
        }
    }
}
