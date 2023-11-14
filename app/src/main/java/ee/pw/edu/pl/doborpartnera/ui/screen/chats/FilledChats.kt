package ee.pw.edu.pl.doborpartnera.ui.screen.chats

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.components.ChatPersonImage
import ee.pw.edu.pl.doborpartnera.ui.components.TextDialog
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun FilledChats(
    modifier: Modifier = Modifier,
    chatPeople: List<ChatPersonDisplayable>,
    onChatClick: (ChatPersonDisplayable) -> Unit,
    onRemoveClick: (ChatPersonDisplayable) -> Unit,
) {
    val openDialog = remember { mutableStateOf<ChatPersonDisplayable?>(null) }
    openDialog.value?.let { chatPerson ->
        TextDialog(
            onDismiss = { openDialog.value = null },
            onConfirm = {
                onRemoveClick(chatPerson)
                openDialog.value = null
            },
            title = stringResource(id = R.string.chats_delete_title, chatPerson.person.name),
            confirm = stringResource(id = R.string.chats_delete_confirm),
        )
    }
    LazyColumn(
        modifier = modifier,
    ) {
        items(chatPeople) { chat ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onChatClick(chat) }
                    .padding(App.dimens.screen_spacing_small),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ChatPersonImage(url = chat.person.avatar)
                Text(
                    modifier = Modifier
                        .weight(1F)
                        .padding(start = App.dimens.views_spacing_medium),
                    text = chat.person.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                )
                if (chat.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(App.dimens.chat_delete_loading_indicator_size))
                } else {
                    IconButton(onClick = { openDialog.value = chat }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                }
            }
        }
    }
}
