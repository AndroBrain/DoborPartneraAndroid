package ee.pw.edu.pl.doborpartnera.ui.screen.match.find

import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class FindMatchState(
   val isLoading: Boolean = false,
) : UiState
