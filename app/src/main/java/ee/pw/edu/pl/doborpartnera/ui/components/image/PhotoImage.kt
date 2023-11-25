package ee.pw.edu.pl.doborpartnera.ui.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun PhotoImage(
    modifier: Modifier = Modifier,
    url: String?,
    width: Dp = App.dimens.profile_gallery_image_width,
    height: Dp = App.dimens.profile_gallery_image_height,
    shape: Shape = MaterialTheme.shapes.small,
    icon: @Composable BoxScope.() -> Unit = {},
) {
    Box(modifier = modifier) {
        GlideImage(
            modifier = Modifier.size(width = width, height = height),
            success = { _, painter ->
                Image(
                    modifier = Modifier
                        .size(width = width, height = height)
                        .clip(shape),
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            },
            imageModel = { url },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                requestSize = with(LocalDensity.current) {
                    IntSize(
                        width = width.roundToPx(),
                        height = height.roundToPx(),
                    )
                }
            ),
            loading = {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            },
        )
        icon()
    }
}
