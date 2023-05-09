package ee.pw.edu.pl.doborpartnera.ui.screen.auth.register

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterState(
    val isLoading: Boolean = false,
    val email: String = "",
    @StringRes val emailError: Int? = null,
    val name: String = "",
    @StringRes val nameError: Int? = null,
    val surname: String = "",
    @StringRes val surnameError: Int? = null,
    val password: String = "",
    val repeatPassword: String = "",
) : UiState
