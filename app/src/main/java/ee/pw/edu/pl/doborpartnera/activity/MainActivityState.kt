package ee.pw.edu.pl.doborpartnera.activity

import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainActivityState(
    val isLoggedIn: Boolean = false,
) : UiState
