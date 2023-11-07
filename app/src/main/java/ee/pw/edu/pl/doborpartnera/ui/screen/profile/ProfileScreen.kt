package ee.pw.edu.pl.doborpartnera.ui.screen.profile

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ee.pw.edu.pl.doborpartnera.ui.components.RefreshBox
import ee.pw.edu.pl.doborpartnera.ui.theme.App
import ee.pw.edu.pl.domain.usecase.profile.EditProfileForm

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
    navigateToEdit: (EditProfileForm) -> Unit,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier,
    ) { insets ->
        Crossfade(
            modifier = Modifier
                .padding(insets)
                .fillMaxSize(),
            targetState = state.value, label = "profileCrossfade",
        ) { state ->
            if (state.isInError) {
                RefreshBox(
                    modifier = Modifier.fillMaxSize(),
                    isLoading = state.isLoading,
                    onClick = viewModel::loadProfile,
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(App.dimens.screen_spacing_medium),
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    } else {
                        LoadedProfile(
                            modifier = Modifier.fillMaxSize(),
                            state = state,
                            onProfileImageChanged = viewModel::changeProfileImage,
                            onEditProfileClicked = {
                                navigateToEdit(
                                    EditProfileForm(
                                        description = state.shortDescription,
                                    )
                                )
                            },
                        )
                    }
                }
            }
        }
    }
}

