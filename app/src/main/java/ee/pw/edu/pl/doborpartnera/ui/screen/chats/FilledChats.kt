package ee.pw.edu.pl.doborpartnera.ui.screen.chats

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ee.pw.edu.pl.doborpartnera.ui.components.ChatPersonImage
import ee.pw.edu.pl.doborpartnera.ui.theme.App
import ee.pw.edu.pl.domain.usecase.chat.ChatPerson

@Composable
fun FilledChats(
    modifier: Modifier = Modifier,
    chatPeople: List<ChatPerson>,
    onChatClick: (ChatPerson) -> Unit,
) {
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
                ChatPersonImage(url = chat.imageUrl)
                Text(
                    modifier = Modifier.padding(start = App.dimens.views_spacing_medium),
                    text = chat.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                )
            }
        }
    }
}
