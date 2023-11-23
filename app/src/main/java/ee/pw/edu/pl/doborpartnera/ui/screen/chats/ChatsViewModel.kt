package ee.pw.edu.pl.doborpartnera.ui.screen.chats

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.result.getMessage
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.domain.core.result.fold
import ee.pw.edu.pl.domain.usecase.message.profile.GetProfilesWithMessagesUseCase
import ee.pw.edu.pl.domain.usecase.message.profile.RemoveProfileWithMessagesUseCase
import ee.pw.edu.pl.domain.usecase.message.profile.UpdateProfilesWithMessagesUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val removeProfileWithMessagesUseCase: RemoveProfileWithMessagesUseCase,
    private val getProfilesWithMessagesUseCase: GetProfilesWithMessagesUseCase,
    private val updateProfilesWithMessagesUseCase: UpdateProfilesWithMessagesUseCase,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<ChatsState>(savedStateHandle, ChatsState()) {
    init {
        getChats()
    }

    fun updateChats() {
        viewModelScope.launch {
            updateState { state -> state.copy(isLoading = state.chatPeople?.isEmpty() == true) }
            updateProfilesWithMessagesUseCase().fold(
                onOk = {
                    updateState { state -> state.copy(isLoading = false) }
                }, onError = { error ->
                    updateState { state ->
                        state.copy(errorMsg = error.type.getMessage(), isLoading = false)
                    }
                }
            )
        }
    }

    fun clearErrorMsg() {
        updateState { state -> state.copy(errorMsg = null) }
    }

    fun removeChatPerson(chatPerson: ChatPersonDisplayable) {
        viewModelScope.launch {
            updateState { state ->
                state.copy(
                    chatPeople = state.chatPeople?.toMutableList()?.apply {
                        val personIndex = indexOfFirst { it.person.id == chatPerson.person.id }
                        if (personIndex >= 0) {
                            this[personIndex] = chatPerson.copy(isLoading = true)
                        }
                    }
                )
            }
            removeProfileWithMessagesUseCase(chatPerson.person.id).fold(
                onOk = { person ->
                    updateState { state ->
                        state.copy(
                            chatPeople = state.chatPeople?.filterNot { it.person.id == chatPerson.person.id },
                        )
                    }
                }, onError = { error ->
                    updateState { state ->
                        state.copy(
                            chatPeople = state.chatPeople?.toMutableList()?.apply {
                                val personIndex = indexOf(chatPerson)
                                if (personIndex >= 0) {
                                    this[personIndex] = chatPerson.copy(isLoading = false)
                                }
                            },
                            errorMsg = error.type.getMessage()
                        )
                    }
                }
            )
        }
    }

    private fun getChats() {
        viewModelScope.launch {
            getProfilesWithMessagesUseCase().onEach { result ->
                updateState { state ->
                    state.copy(chatPeople = result.map(::ChatPersonDisplayable))
                }
            }.launchIn(this)
        }
    }
}
