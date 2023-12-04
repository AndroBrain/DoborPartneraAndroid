package ee.pw.edu.pl.doborpartnera.ui.screen.test

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import ee.pw.edu.pl.domain.usecase.account.TestAnswer
import ee.pw.edu.pl.domain.usecase.account.TestQuestion
import kotlinx.parcelize.Parcelize

@Parcelize
data class TestState(
    @StringRes val errorMsg: Int? = null,
    val qa: Map<TestQuestion, TestAnswer> = mapOf(),
    val expandedQuestion: TestQuestion? = null,
) : UiState
