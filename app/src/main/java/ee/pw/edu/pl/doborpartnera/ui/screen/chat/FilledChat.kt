package ee.pw.edu.pl.doborpartnera.ui.screen.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import ee.pw.edu.pl.doborpartnera.ui.theme.App
import ee.pw.edu.pl.domain.usecase.chat.Chat

@Composable
fun FilledChat(
    modifier: Modifier = Modifier,
    chats: List<Chat>,
    onChatClick: (Chat) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(chats) { chat ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onChatClick(chat) }
                    .padding(App.dimens.screen_spacing_small),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                GlideImage(
                    modifier = Modifier.size(App.dimens.chat_image_size),
                    success = { _, painter ->
                        Image(
                            modifier = Modifier
                                .size(App.dimens.chat_image_size)
                                .clip(CircleShape),
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                    },
                    imageModel = {
                        chat.imageUrl
                    },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        requestSize = with(LocalDensity.current) {
                            IntSize(
                                width = App.dimens.chat_image_size.roundToPx(),
                                height = App.dimens.chat_image_size.roundToPx(),
                            )
                        }
                    ),
                )
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
