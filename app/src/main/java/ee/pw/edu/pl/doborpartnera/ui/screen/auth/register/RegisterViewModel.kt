package ee.pw.edu.pl.doborpartnera.ui.screen.auth.register

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<RegisterState>(savedStateHandle, RegisterState()) {

    fun changeEmail(email: String) {
        updateState { state -> state.copy(email = email) }
    }

    fun changeName(name: String) {
        updateState { state -> state.copy(name = name) }
    }

    fun changeSurname(surname: String) {
        updateState { state -> state.copy(surname = surname) }
    }

    fun changePassword(password: String) {
        updateState { state -> state.copy(password = password) }
    }

    fun changeRepeatPassword(repeatPassword: String) {
        updateState { state -> state.copy(repeatPassword = repeatPassword) }
    }

    fun register() {
        TODO("Not yet implemented")
    }

}
