package ee.pw.edu.pl.doborpartnera.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun ChatPersonImage(
    modifier: Modifier = Modifier,
    url: String,
) {
    GlideImage(
        modifier = modifier.then(Modifier.size(App.dimens.chat_image_size)),
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
            url
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
}
