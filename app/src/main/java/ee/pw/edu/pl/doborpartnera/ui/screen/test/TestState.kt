package ee.pw.edu.pl.doborpartnera.ui.screen.test

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class TestState(
    @StringRes val errorMsg: Int? = null,
) : UiState
