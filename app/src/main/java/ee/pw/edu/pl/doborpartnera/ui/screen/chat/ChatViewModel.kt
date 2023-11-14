package ee.pw.edu.pl.doborpartnera.ui.screen.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.result.getMessage
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.domain.core.result.fold
import ee.pw.edu.pl.domain.usecase.chat.SendMessageForm
import ee.pw.edu.pl.domain.usecase.chat.SendMessageUseCase
import ee.pw.edu.pl.domain.usecase.chat.SubscribeToChatUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val subscribeToChatUseCase: SubscribeToChatUseCase,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<ChatState>(savedStateHandle, ChatState()) {
    private val args = ChatArgs(savedStateHandle)

    init {
        updateState { state ->
            state.copy(
                id = args.id,
                name = args.name,
                imageUrl = args.imageUrl
            )
        }
        subscribeToChat()
    }

    fun clearErrorMsg() {
        updateState { state -> state.copy(errorMsg = null) }
    }

    fun updateMessage(message: String) {
        updateState { state -> state.copy(message = message) }
    }

    fun sendMessage() {
        viewModelScope.launch {
            val currentState = state.value
            sendMessageUseCase(
                SendMessageForm(
                    receiverId = currentState.id,
                    message = currentState.message,
                )
            )
//                .onEach { result ->
//                result.fold(
//                    onOk = {
            updateState { state ->
                state.copy(
                    messagesBeingSent = state.messagesBeingSent - 1,
                )
            }
//                    }, onError = { error ->
//                        updateState { state ->
//                            state.copy(
//                                errorMsg = error.type.getMessage(),
//                                messagesBeingSent = state.messagesBeingSent - 1,
//                            )
//                        }
//                    }
//                )
//            }.launchIn(this)
        }
        updateState { state ->
            state.copy(
                messagesBeingSent = state.messagesBeingSent + 1,
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
