package ee.pw.edu.pl.doborpartnera.ui.screen.match.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ee.pw.edu.pl.doborpartnera.ui.components.RefreshBox
import ee.pw.edu.pl.doborpartnera.ui.components.match.Match
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun MatchProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: MatchProfileViewModel,
    navigateUp: () -> Unit,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    state.value.errorMsg?.let { errorMsg ->
        val context = LocalContext.current
        LaunchedEffect(errorMsg) {
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
            viewModel.clearErrorMsg()
        }
    }
    Scaffold(
        modifier = modifier,
    ) { insets ->
        Box(
            modifier = Modifier
                .padding(insets)
                .fillMaxSize()
        ) {
            val profile = state.value.profile
            when {
                state.value.isInError -> {
                    RefreshBox(
                        modifier = Modifier.align(Alignment.Center),
                        isLoading = false,
                        onClick = viewModel::loadProfile,
                    )
                }

                profile == null -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                else -> {
                    Match(profile = profile)
                }
            }
        }
        IconButton(
            modifier = Modifier.padding(
                top = App.dimens.screen_spacing_small,
                start = App.dimens.screen_spacing_small
            ),
            onClick = navigateUp,
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}