package ee.pw.edu.pl.doborpartnera.ui.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun PhotoImageButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    onClick: () -> Unit,
    width: Dp = App.dimens.profile_gallery_image_width,
    height: Dp = App.dimens.profile_gallery_image_height,
    shape: Shape = MaterialTheme.shapes.small,
    painterToBorderSpacing: Dp = App.dimens.views_spacing_small,
) {
    Box(
        modifier = modifier
            .size(width = width, height = height)
            .border(
                width = App.dimens.border_width,
                color = MaterialTheme.colorScheme.onSurface,
                shape = shape,
            )
            .clip(shape)
            .clickable(onClick = onClick),
    ) {
        val size = if (width < height) width else height
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .size(size)
                .padding(painterToBorderSpacing)
                .clip(shape),
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary),
        )
    }
}
