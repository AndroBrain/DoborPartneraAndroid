package ee.pw.edu.pl.doborpartnera.ui.screen.match.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.result.getMessage
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.doborpartnera.ui.screen.match.MatchProfileDisplayable
import ee.pw.edu.pl.domain.core.result.fold
import ee.pw.edu.pl.domain.usecase.match.GetMatchProfileUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MatchProfileViewModel @Inject constructor(
    private val getMatchProfileUseCase: GetMatchProfileUseCase,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<MatchProfileState>(savedStateHandle, MatchProfileState()) {
    private val id = MatchProfileArgs(savedStateHandle).id

    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            updateState { state -> state.copy(isInError = false) }
            getMatchProfileUseCase(id).fold(
                onOk = { profile ->
                    updateState { state -> state.copy(profile = MatchProfileDisplayable(profile.value)) }
                }, onError = { error ->
                    updateState { state ->
                        state.copy(
                            errorMsg = error.type.getMessage(),
                            isInError = true
                        )
                    }
                }
            )
        }
    }

    fun clearErrorMsg() {
        updateState { state -> state.copy(errorMsg = null) }
    }
}
