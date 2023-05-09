package ee.pw.edu.pl.doborpartnera.ui.screen.auth.login

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.validation.EmailValidator
import ee.pw.edu.pl.doborpartnera.core.validation.Validator
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : SingleStateViewModel<LoginState>(savedStateHandle, LoginState()) {

    fun changeEmail(name: String) {
        updateState { state -> state.copy(email = name, emailError = null) }
    }

    fun changePassword(password: String) {
        updateState { state -> state.copy(password = password) }
    }

    fun login() {
        val currentState = state.value
        val emailError = Validator.validate(currentState.email, EmailValidator)
        if (emailError != null) {
            updateState { state -> state.copy(emailError = emailError) }
        }
    }
}
