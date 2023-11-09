package ee.pw.edu.pl.doborpartnera.ui.screen.profile

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.components.RefreshBox
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
    navigateToEdit: () -> Unit,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = navigateToEdit,
                icon = { Icon(imageVector = Icons.Default.Edit, contentDescription = null) },
                text = { Text(text = stringResource(id = R.string.profile_edit)) },
            )
        }
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
                            onEditProfileClicked = { navigateToEdit() },
                        )
                    }
                }
            }
        }
    }
}
