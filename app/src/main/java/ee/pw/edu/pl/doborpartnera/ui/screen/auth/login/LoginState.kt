package ee.pw.edu.pl.doborpartnera.ui.screen.auth.login

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginState(
    val isLoading: Boolean = false,
    val email: String = "",
    @StringRes val emailError: Int? = null,
    val password: String = "",
) : UiState
