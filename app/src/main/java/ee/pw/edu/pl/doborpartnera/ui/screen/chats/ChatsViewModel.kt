package ee.pw.edu.pl.doborpartnera.ui.screen.chats

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.result.getMessage
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.domain.core.result.fold
import ee.pw.edu.pl.domain.usecase.chat.GetChatsUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val getChatsUseCase: GetChatsUseCase,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<ChatsState>(savedStateHandle, ChatsState()) {
    init {
        getChats()
    }

    fun getChats() {
        viewModelScope.launch {
            updateState { state -> state.copy(isLoading = true, isInError = false) }
            getChatsUseCase().onEach { result ->
                result.fold(
                    onOk = {
                        updateState { state -> state.copy(chats = it.value, isLoading = false) }
                    }, onError = { error ->
                        updateState { state ->
                            state.copy(
                                errorMsg = error.type.getMessage(),
                                isInError = true,
                                isLoading = false,
                            )
                        }
                    }
                )
            }.launchIn(this)
        }
    }

    fun clearErrorMsg() {
        updateState { state -> state.copy(errorMsg = null) }
    }
}
