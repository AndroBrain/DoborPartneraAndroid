package ee.pw.edu.pl.doborpartnera.ui.screen.auth.register

import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterState(
    val isLoading: Boolean = false,
    val email: String = "",
    val name: String = "",
    val surname: String = "",
    val password: String = "",
    val repeatPassword: String = "",
) : UiState
