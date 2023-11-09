package ee.pw.edu.pl.doborpartnera.ui.screen.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    url: String?,
    enabled: Boolean,
    onImageChanged: ((Uri) -> Unit)? = null,
    size: Dp = App.dimens.profile_image_size,
) {
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
            if (uri != null && onImageChanged != null) {
                onImageChanged(uri)
            }
        }
    )
    GlideImage(
        modifier = modifier.size(size),
        success = { _, painter ->
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(size)
                    .clip(CircleShape)
                    .clickable(
                        enabled = enabled,
                        onClick = {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                    ),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        },
        failure = {
            OutlinedIconButton(
                onClick = {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                enabled = enabled
            ) {
                Icon(
                    modifier = Modifier
                        .size(size)
                        .padding(App.dimens.views_spacing_medium),
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        },
        imageModel = { selectedImageUri ?: url },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop,
            requestSize = with(LocalDensity.current) {
                IntSize(
                    width = App.dimens.profile_image_size.roundToPx(),
                    height = App.dimens.profile_image_size.roundToPx(),
                )
            }
        ),
    )
}
