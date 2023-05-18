package ee.pw.edu.pl.doborpartnera.ui.screen.auth.register

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import ee.pw.edu.pl.domain.usecase.profile.Gender
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
    @StringRes val passwordError: Int? = null,
    val repeatPassword: String = "",
    @StringRes val repeatPasswordError: Int? = null,
    val birthdate: Long? = null,
    @StringRes val birthdateError: Int? = null,
    val gender: Gender? = null,
    @StringRes val genderError: Int? = null,
    @StringRes val errorMsg: Int? = null,
    val isRegistered: Boolean = false,
) : UiState
