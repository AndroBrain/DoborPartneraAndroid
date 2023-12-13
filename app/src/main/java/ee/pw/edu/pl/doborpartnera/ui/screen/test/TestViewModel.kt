package ee.pw.edu.pl.doborpartnera.ui.screen.test

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.core.result.getMessage
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.domain.core.result.fold
import ee.pw.edu.pl.domain.usecase.test.SetTestForm
import ee.pw.edu.pl.domain.usecase.test.SetTestUseCase
import ee.pw.edu.pl.domain.usecase.test.TestAnswer
import ee.pw.edu.pl.domain.usecase.test.TestQuestion
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class TestViewModel @Inject constructor(
    private val setTestUseCase: SetTestUseCase,
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
            val qa = currentState.qa
            val form = SetTestForm(
                eyes = qa[TestQuestion.EYES] ?: return,
                hair = qa[TestQuestion.HAIR] ?: return,
                tattoo = qa[TestQuestion.TATTOO] ?: return,
                sport = qa[TestQuestion.SPORT] ?: return,
                education = qa[TestQuestion.EDUCATION] ?: return,
                recreation = qa[TestQuestion.RECREATION] ?: return,
                family = qa[TestQuestion.FAMILY] ?: return,
                charity = qa[TestQuestion.CHARITY] ?: return,
                people = qa[TestQuestion.PEOPLE] ?: return,
                wedding = qa[TestQuestion.WEDDING] ?: return,
                belief = qa[TestQuestion.BELIEF] ?: return,
                money = qa[TestQuestion.MONEY] ?: return,
                religious = qa[TestQuestion.RELIGIOUS] ?: return,
                mind = qa[TestQuestion.MIND] ?: return,
                humour = qa[TestQuestion.HUMOUR] ?: return,
            )
            viewModelScope.launch {
                val result = setTestUseCase(form)
                result.fold(
                    onOk = {
                        updateState { state ->
                            state.copy(
                                isLoading = false,
                                isFilled = true,
                            )
                        }
                    },
                    onError = {
                        updateState { state ->
                            state.copy(
                                isLoading = false,
                                errorMsg = it.type.getMessage(),
                            )
                        }
                    }
                )
            }
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
