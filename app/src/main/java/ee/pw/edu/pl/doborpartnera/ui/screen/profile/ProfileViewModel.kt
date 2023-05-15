package ee.pw.edu.pl.doborpartnera.ui.screen.profile

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.pw.edu.pl.doborpartnera.core.viewmodel.SingleStateViewModel
import ee.pw.edu.pl.domain.core.result.fold
import ee.pw.edu.pl.domain.usecase.profile.GetProfileUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<ProfileState>(savedStateHandle, ProfileState()) {
    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            updateState { state -> state.copy(isInError = false, isLoading = true) }
            getProfileUseCase().onEach { result ->
                result.fold(
                    onOk = {
                        val profile = it.value
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
                    },
                    onError = {
                        updateState { state -> state.copy(isLoading = false, isInError = true) }
                    },
                )
            }.launchIn(this)
        }
    }

    fun changeProfileImage(uri: Uri) {
        // TODO add request for change
    }
}
