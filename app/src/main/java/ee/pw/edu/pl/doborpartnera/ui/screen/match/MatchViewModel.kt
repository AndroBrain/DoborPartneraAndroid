package ee.pw.edu.pl.doborpartnera.ui.screen.match

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.result.getMessage
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.domain.core.result.fold
import ee.pw.edu.pl.domain.usecase.account.GetIsAccountFilledUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val getIsAccountFilledUseCase: GetIsAccountFilledUseCase,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<MatchState>(savedStateHandle, MatchState()) {
    init {
        loadIsProfileFilled()
    }

    fun clearErrorMsg() {
        updateState { state -> state.copy(errorMsg = null) }
    }

    fun loadIsProfileFilled() {
        viewModelScope.launch {
            updateState { state -> state.copy(isLoading = true, isInError = false) }
            getIsAccountFilledUseCase().onEach { result ->
                result.fold(
                    onOk = {
                        updateState { state ->
                            state.copy(
                                isProfileFilled = it.value, isLoading = false,
                            )
                        }
                    }, onError = { error ->
                        updateState { state ->
                            state.copy(
                                isInError = true,
                                errorMsg = error.type.getMessage(),
                                isLoading = false,
                            )
                        }
                    }
                )
            }.launchIn(this)
        }
    }
}
