package ee.pw.edu.pl.doborpartnera.ui.screen.auth.login

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : SingleStateViewModel<LoginState>(savedStateHandle, LoginState()) {

    fun changeEmail(name: String) {
        updateState { state -> state.copy(email = name) }
    }

    fun changePassword(password: String) {
        updateState { state -> state.copy(password = password) }
    }

    fun login() {

    }
}
