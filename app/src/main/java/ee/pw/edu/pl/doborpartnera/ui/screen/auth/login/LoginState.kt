package ee.pw.edu.pl.doborpartnera.ui.screen.auth.login

import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginState(
    val isLoading: Boolean = false,
    val username: String = "",
    val password: String = "",
) : UiState
