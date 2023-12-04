package ee.pw.edu.pl.doborpartnera.ui.screen.test

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.domain.usecase.account.TestAnswer
import ee.pw.edu.pl.domain.usecase.account.TestQuestion
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<TestState>(savedStateHandle, TestState()) {
    fun clearErrorMsg() {
        updateState { state -> state.copy(errorMsg = null) }
    }

    fun setAnswer(question: TestQuestion, answer: TestAnswer) {
        updateState { state ->
            state.copy(
                qa = state.qa.toMutableMap().apply {
                    this[question] = answer
                }
            )
        }
    }

    fun setExpandedQuestion(question: TestQuestion?) {
        updateState { state -> state.copy(expandedQuestion = question) }
    }
}
