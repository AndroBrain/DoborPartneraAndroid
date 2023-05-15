package ee.pw.edu.pl.doborpartnera.ui.screen.profile

import android.net.Uri
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileImagesGallery(
    modifier: Modifier = Modifier,
    uris: List<Uri>,
    pickImages: () -> Unit,
) {
    Crossfade(targetState = uris.isNotEmpty()) { hasImages ->
        if (hasImages) {
            FlowRow(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                uris.forEach { uri ->
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
                        imageModel = { uri },
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = App.dimens.views_spacing_medium),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    modifier = Modifier.size(App.dimens.profile_gallery_empty_image_size),
                    imageVector = Icons.Default.PhotoCamera,
                    contentDescription = null,
                )
                Button(
                    onClick = pickImages,
                ) {
                    Text(text = stringResource(id = R.string.profile_empty_images))
                }
            }
        }
    }
}
