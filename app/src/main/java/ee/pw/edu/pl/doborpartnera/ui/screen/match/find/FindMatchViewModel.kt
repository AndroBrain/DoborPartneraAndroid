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
            updateState { state -> state.copy(isLoading = true) }
            getMatchesUseCase().onEach { result ->
                result.fold(
                    onOk = {
                        updateState { state -> state.copy(profiles = it.value) }
                    }, onError = { error ->
                        updateState { state ->
                            state.copy(
                                isLoading = false,
                                errorMsg = error.type.getMessage(),
                            )
                        }
                    }
                )
            }.launchIn(this)
        }
    }
}
