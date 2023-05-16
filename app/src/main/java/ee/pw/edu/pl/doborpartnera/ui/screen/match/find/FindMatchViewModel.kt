package ee.pw.edu.pl.doborpartnera.ui.screen.match.find

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.result.getMessage
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.domain.core.result.fold
import ee.pw.edu.pl.domain.usecase.match.GetMatchesUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private const val LOAD_MORE_INDICATOR = 3

@HiltViewModel
class FindMatchViewModel @Inject constructor(
    private val getMatchesUseCase: GetMatchesUseCase,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<FindMatchState>(savedStateHandle, FindMatchState()) {
    init {
        findMatches()
    }

    fun findMatches() {
        viewModelScope.launch {
            updateState { state -> state.copy(isLoading = true, isInError = false) }
            getMatchesUseCase().onEach { result ->
                result.fold(
                    onOk = {
                        updateState { state -> state.copy(profiles = state.profiles + it.value) }
                    }, onError = { error ->
                        updateState { state ->
                            state.copy(
                                isLoading = false,
                                isInError = true,
                                errorMsg = error.type.getMessage(),
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

    fun accept() {
        updateState { state ->
            if (state.profiles.size - state.profileIndex < LOAD_MORE_INDICATOR) {
                findMatches()
            }
            state.copy(profileIndex = state.profileIndex + 1)
        }
    }

    fun decline() {
        updateState { state ->
            if (state.profiles.size - state.profileIndex < LOAD_MORE_INDICATOR) {
                findMatches()
            }
            state.copy(profileIndex = state.profileIndex + 1)
        }
    }
}
