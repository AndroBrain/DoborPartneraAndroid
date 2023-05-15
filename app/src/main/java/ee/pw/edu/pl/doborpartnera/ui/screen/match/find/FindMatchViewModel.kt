package ee.pw.edu.pl.doborpartnera.ui.screen.match.find

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import javax.inject.Inject

@HiltViewModel
class FindMatchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<FindMatchState>(savedStateHandle, FindMatchState()) {

}
