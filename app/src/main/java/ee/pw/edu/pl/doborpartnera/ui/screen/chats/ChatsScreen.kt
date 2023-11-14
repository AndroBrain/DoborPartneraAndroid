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
import ee.pw.edu.pl.domain.usecase.chat.profile.ChatProfile

@Composable
fun ChatsScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatsViewModel,
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

                    state.chatPeople == null -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    state.chatPeople.isEmpty() -> {
                        EmptyChats(modifier = Modifier.align(Alignment.Center))
                    }

                    else -> {
                        FilledChats(
                            chatPeople = state.chatPeople,
                            onChatClick = { navigateToChat(it.person) },
                            onRemoveClick = viewModel::removeChatPerson,
                        )
                    }
                }
            }
        }
    }
}
