package ee.pw.edu.pl.doborpartnera.ui.screen.test

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.R
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
                },
                errorQuestions = state.errorQuestions.toMutableSet().apply {
                    remove(question)
                }
            )
        }
    }

    fun setExpandedQuestion(question: TestQuestion?) {
        updateState { state -> state.copy(expandedQuestion = question) }
    }

    fun setTest() {
        updateState { state -> state.copy(isLoading = true) }
        val currentState = state.value
        val unfilledQuestions = TestQuestion.entries.filterNot { question ->
            currentState.qa.containsKey(question)
        }.toSet()
        if (unfilledQuestions.isEmpty()) {
            updateState { state -> state.copy(isLoading = false) }
        } else {
            updateState { state ->
                state.copy(
                    errorQuestions = unfilledQuestions,
                    isLoading = false,
                    errorMsg = R.string.test_err_not_filled,
                )
            }
        }
    }
}
