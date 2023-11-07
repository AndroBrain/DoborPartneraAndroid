package ee.pw.edu.pl.doborpartnera.activity

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.domain.usecase.auth.login.IsLoggedInUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    isLoggedInUseCase: IsLoggedInUseCase,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<MainActivityState>(savedStateHandle, MainActivityState()) {
    init {
        runBlocking {
            val isLoggedIn = isLoggedInUseCase().first()
            updateState { state -> state.copy(isLoggedIn = isLoggedIn) }
        }
    }
}
