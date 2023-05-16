package ee.pw.edu.pl.doborpartnera.ui.screen.chats

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
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatsViewModel,
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
//        TODO add search bar?
    ) { insets ->
        Crossfade(
            modifier = Modifier
                .padding(insets)
                .fillMaxSize(),
            targetState = state.value,
        ) { state ->
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                when {
                    state.isInError -> {
                        RefreshBox(
                            modifier = Modifier.fillMaxSize(),
                            isLoading = state.isLoading,
                            onClick = viewModel::getChats,
                        )
                    }

                    state.chats == null -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    state.chats.isEmpty() -> {
                        EmptyChat(modifier = Modifier.align(Alignment.Center))
                    }

                    else -> {
                        FilledChat(chats = state.chats, onChatClick = {/*TODO*/ })
                    }
                }
            }
        }
    }
}
