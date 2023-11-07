package ee.pw.edu.pl.doborpartnera.ui.screen.profile

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileImagesGallery(
    modifier: Modifier = Modifier,
    urls: List<String>,
    pickImages: () -> Unit,
) {
    Crossfade(targetState = urls.isNotEmpty(), label = "GalleryCrossfade") { hasImages ->
        if (hasImages) {
            FlowRow(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                urls.forEach { url ->
                    GlideImage(
                        modifier = Modifier
                            .padding(bottom = App.dimens.views_spacing_small)
                            .size(App.dimens.profile_gallery_image_size),
                        success = { _, painter ->
                            Image(
                                modifier = Modifier
                                    .size(App.dimens.profile_gallery_image_size)
                                    .clip(MaterialTheme.shapes.small),
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
                                    width = App.dimens.profile_gallery_image_size.roundToPx(),
                                    height = App.dimens.profile_gallery_image_size.roundToPx(),
                                )
                            }
                        ),
                    )
                }
            }
        } else {
            OutlinedIconButton(
                modifier = Modifier
                    .size(App.dimens.profile_image_size),
                onClick = pickImages,
            ) {
                Icon(
                    modifier = Modifier
                        .size(App.dimens.profile_image_size)
                        .padding(App.dimens.views_spacing_medium),
                    painter = painterResource(id = R.drawable.ic_gallery_thumbnail),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}
