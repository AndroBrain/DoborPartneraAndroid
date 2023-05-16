package ee.pw.edu.pl.doborpartnera.ui.screen.match.find

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import ee.pw.edu.pl.domain.usecase.match.MatchProfile
import kotlinx.parcelize.Parcelize

@Parcelize
data class FindMatchState(
    val isLoading: Boolean = false,
    val profiles: List<MatchProfile> = emptyList(),
    val profileIndex: Int = 0,
    @StringRes val errorMsg: Int? = null,
    val isInError: Boolean = false,
) : UiState
