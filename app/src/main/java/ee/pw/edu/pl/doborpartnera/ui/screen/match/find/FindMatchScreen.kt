package ee.pw.edu.pl.doborpartnera.ui.screen.match.find

import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ee.pw.edu.pl.doborpartnera.ui.components.RefreshBox

@Composable
fun FindMatchScreen(
    modifier: Modifier = Modifier,
    viewModel: FindMatchViewModel,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    state.value.errorMsg?.let { errorMsg ->
        val context = LocalContext.current
        LaunchedEffect(errorMsg) {
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
            viewModel.clearErrorMsg()
        }
    }
    Scaffold(modifier = modifier) { insets ->
        Crossfade(modifier = Modifier.padding(insets), targetState = state.value) { state ->

            Box(modifier = Modifier.fillMaxSize()) {
                when {
                    state.isInError -> {
                        RefreshBox(
                            modifier = Modifier.align(Alignment.Center),
                            isLoading = state.isLoading,
                            onClick = viewModel::findMatches,
                        )
                    }

                    state.profiles.isNotEmpty() -> {
                        val profile = state.profiles.getOrNull(state.profileIndex)
                        if (profile == null) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        } else {
                            Match(
                                modifier = Modifier.fillMaxSize(),
                                profile = profile,
                                onAccept = viewModel::accept,
                                onDecline = viewModel::decline,
                            )
                        }
                    }

                    state.isLoading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    }
}
