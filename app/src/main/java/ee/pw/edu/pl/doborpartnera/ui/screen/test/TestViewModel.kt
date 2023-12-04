package ee.pw.edu.pl.doborpartnera.ui.screen.test

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<TestState>(savedStateHandle, TestState()) {
    fun clearErrorMsg() {
        updateState { state -> state.copy(errorMsg = null) }
    }
}
