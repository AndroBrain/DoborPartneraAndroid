package ee.pw.edu.pl.doborpartnera.ui.screen.match.profile

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import ee.pw.edu.pl.doborpartnera.ui.screen.match.MatchProfileDisplayable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchProfileState(
    val profile: MatchProfileDisplayable? = null,
    val profileIndex: Int = 0,
    @StringRes val errorMsg: Int? = null,
    val isInError: Boolean = false,
) : UiState
