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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /*TODO navigate to edit screen*/ },
                text = { Text(text = stringResource(id = R.string.profile_edit)) },
                icon = { Icon(imageVector = Icons.Default.Edit, contentDescription = null) },
            )
        },
    ) { insets ->
        Crossfade(
            modifier = Modifier
                .padding(insets)
                .fillMaxSize(),
            targetState = state.value.isLoading
        ) { isLoading ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(App.dimens.screen_spacing_medium),
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else {
                    LoadedProfile(
                        modifier = Modifier.fillMaxSize(),
                        state = state.value,
                    )
                }
            }
        }
    }
}

