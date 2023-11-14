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
import ee.pw.edu.pl.doborpartnera.ui.components.match.Match
import ee.pw.edu.pl.domain.usecase.chat.profile.ChatProfile

@Composable
fun FindMatchScreen(
    modifier: Modifier = Modifier,
    viewModel: FindMatchViewModel,
    navigateToChat: (ChatProfile) -> Unit,
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
//        TODO remove crossfades when we load next profiles
        Crossfade(
            modifier = Modifier.padding(insets),
            targetState = state.value.profileIndex,
            label = "MatchCrossfade",
        ) { index ->
            val stateValue = state.value

            Box(modifier = Modifier.fillMaxSize()) {
                when {
                    stateValue.isInError -> {
                        RefreshBox(
                            modifier = Modifier.align(Alignment.Center),
                            isLoading = stateValue.isLoading,
                            onClick = viewModel::findMatches,
                        )
                    }

                    stateValue.profiles.isNotEmpty() -> {
                        val profile = stateValue.profiles.getOrNull(index)
                        if (profile == null) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        } else {
                            Match(
                                modifier = Modifier.fillMaxSize(),
                                profile = profile,
                                onAccept = {
                                    viewModel.accept()
                                    navigateToChat(
                                        ChatProfile(
                                            id = profile.id,
                                            name = profile.name,
                                            avatar = profile.avatar,
                                            messages = emptyList(),
                                        )
                                    )
                                },
                                onDecline = viewModel::decline,
                            )
                        }
                    }

                    stateValue.isLoading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    }
}
