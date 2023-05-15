package ee.pw.edu.pl.doborpartnera.ui.screen.match

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchState(
    val isProfileFilled: Boolean = false,
    val isLoading: Boolean = false,
    @StringRes val errorMsg: Int? = null,
    val isInError: Boolean = false,
) : UiState
