package ee.pw.edu.pl.doborpartnera.ui.screen.profile

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.domain.usecase.profile.GetProfileUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    getProfileUseCase: GetProfileUseCase,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<ProfileState>(savedStateHandle, ProfileState()) {
    init {
        viewModelScope.launch {
            val profile = getProfileUseCase()
            updateState { state ->
                state.copy(
                    email = profile.email,
                    name = profile.name,
                    surname = profile.surname,
                    imageUrl = profile.imageUrl,
                    shortDescription = profile.shortDescription,
                    isLoading = false,
                )
            }
        }
    }

    fun changeProfileImage(uri: Uri) {
        // TODO add request for change
    }
}
