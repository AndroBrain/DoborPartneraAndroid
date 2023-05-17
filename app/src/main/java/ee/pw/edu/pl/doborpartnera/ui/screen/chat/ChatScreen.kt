package ee.pw.edu.pl.doborpartnera.ui.screen.chat

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.components.ChatPersonImage
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel,
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
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets.systemBars.add(WindowInsets.ime),
        topBar = {
            CenterAlignedTopAppBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                title = { Text(text = state.value.name) },
                actions = { ChatPersonImage(url = state.value.imageUrl) },
            )
        },
    ) { insets ->
//        TODO add LazyColumn that with topBarState that will change colors, figure out automatic scrolling when user is at the bottom, consider adding sound
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(insets),
        ) {
            LazyColumn(
                modifier = Modifier.weight(1F),
                contentPadding = PaddingValues(bottom = App.dimens.views_spacing_medium),
            ) {
                items(state.value.chats) { chat ->
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .align(if (chat.isYour) Alignment.CenterEnd else Alignment.CenterStart)
                                .padding(
                                    start = if (chat.isYour) 0.dp else App.dimens.screen_spacing_small,
                                    top = App.dimens.views_spacing_extra_small,
                                    end = if (chat.isYour) App.dimens.screen_spacing_small else 0.dp,
                                )
                                .background(
                                    color = if (chat.isYour) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
                                    shape = MaterialTheme.shapes.medium,
                                )
                                .padding(vertical = App.dimens.chat_message_vertical_padding)
                                .padding(horizontal = App.dimens.chat_message_horizontal_padding)
                        ) {
                            Text(
                                modifier = Modifier.widthIn(min = App.dimens.chat_message_min_width),
                                text = chat.text,
                                color = if (chat.isYour) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onPrimary,
                                textAlign = if (chat.isYour) TextAlign.End else TextAlign.Start,
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = App.dimens.views_spacing_small)
                    .padding(horizontal = App.dimens.screen_spacing_small)
                    .padding(bottom = App.dimens.screen_spacing_small),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(1F),
                    value = state.value.message,
                    onValueChange = viewModel::updateMessage,
                    placeholder = {
                        Text(text = stringResource(id = R.string.chat_message_placeholder))
                    },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    maxLines = 3,
                )
                IconButton(onClick = viewModel::sendMessage) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
        }
    }
}
