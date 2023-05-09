package ee.pw.edu.pl.doborpartnera.ui.screen.chat

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier,
//        TODO add search bar?
    ) { insets ->
        Crossfade(
            modifier = Modifier
                .padding(insets)
                .fillMaxSize(),
            targetState = state.value.chats,
        ) { chats ->
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                when {
                    chats == null -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    chats.isEmpty() -> {
                        EmptyChat(modifier = Modifier.align(Alignment.Center))
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                        ) {

                        }
                    }
                }
            }
        }
    }
}
