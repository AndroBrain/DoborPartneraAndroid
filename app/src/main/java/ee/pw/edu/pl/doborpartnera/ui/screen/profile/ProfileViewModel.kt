package ee.pw.edu.pl.doborpartnera.ui.screen.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.domain.core.result.fold
import ee.pw.edu.pl.domain.usecase.profile.GetProfileUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<ProfileState>(savedStateHandle, ProfileState()) {
    fun loadProfile() {
        viewModelScope.launch {
            updateState { state -> state.copy(isInError = false, isLoading = true) }
            getProfileUseCase().fold(
                onOk = {
                    val profile = it.value
                    updateState { state ->
                        state.copy(
                            name = profile.name,
                            surname = profile.surname,
                            avatar = profile.avatar,
                            description = profile.shortDescription,
                            images = profile.images,
                            interests = profile.interests,
                            isLoading = false,
                        )
                    }
                },
                onError = {
                    updateState { state -> state.copy(isLoading = false, isInError = true) }
                },
            )
        }
    }
}
