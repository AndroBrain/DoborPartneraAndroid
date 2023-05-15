package ee.pw.edu.pl.doborpartnera.ui.screen.auth.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.validation.EmailValidator
import ee.pw.edu.pl.doborpartnera.core.validation.Validator
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        viewModelScope.launch {
            updateState { state -> state.copy(isLoading = true) }
            val currentState = state.value
            val emailError = Validator.validate(currentState.email, EmailValidator)
            if (emailError != null) {
                updateState { state -> state.copy(emailError = emailError, isLoading = false) }
                return@launch
            }
//        TODO call LoginUseCase
            delay(2000L)
            updateState { state -> state.copy(isLoading = false) }
        }
    }
}
