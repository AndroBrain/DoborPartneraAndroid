package ee.pw.edu.pl.doborpartnera.ui.screen.auth.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.core.result.getMessage
import ee.pw.edu.pl.doborpartnera.core.validation.EmailValidator
import ee.pw.edu.pl.doborpartnera.core.validation.Validator
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.domain.core.result.fold
import ee.pw.edu.pl.domain.usecase.auth.login.LoginForm
import ee.pw.edu.pl.domain.usecase.auth.login.LoginUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    savedStateHandle: SavedStateHandle
) : SingleStateViewModel<LoginState>(savedStateHandle, LoginState()) {

    fun changeEmail(name: String) {
        updateState { state -> state.copy(email = name, emailError = null) }
    }

    fun changePassword(password: String) {
        updateState { state -> state.copy(password = password) }
    }

    fun clearErrorMsg() {
        updateState { state -> state.copy(errorMsg = null) }
    }

    fun loggedIn() {
        updateState { state -> state.copy(result = null) }
    }

    fun login() {
        viewModelScope.launch {
            updateState { state -> state.copy(isLoading = true) }
            val currentState = state.value
            val emailError = Validator.validate(currentState.email, EmailValidator)
            if (emailError != null) {
                updateState { state ->
                    state.copy(
                        emailError = emailError,
                        isLoading = false,
                        errorMsg = R.string.result_err_invalid_data,
                    )
                }
                return@launch
            }
            loginUseCase(
                LoginForm(email = currentState.email, password = currentState.password)
            ).onEach { result ->
                result.fold(
                    onOk = { isFilled ->
                        updateState { state ->
                            state.copy(
                                result = if (isFilled.value) {
                                    LoginResultDisplayable.PROFILE_FILLED
                                } else {
                                    LoginResultDisplayable.PROFILE_UNFILLED
                                },
                                isLoading = false,
                            )
                        }
                    }, onError = { error ->
                        updateState { state ->
                            state.copy(
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
