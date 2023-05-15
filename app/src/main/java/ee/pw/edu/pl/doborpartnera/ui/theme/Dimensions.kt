package ee.pw.edu.pl.doborpartnera.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimensions(
    val screen_spacing_extra_small: Dp = 8.dp,
    val screen_spacing_small: Dp = 12.dp,
    val screen_spacing_medium: Dp = 16.dp,
    val screen_spacing_large: Dp = 32.dp,

    val views_spacing_extra_small: Dp = 2.dp,
    val views_spacing_small: Dp = 8.dp,
    val views_spacing_medium: Dp = 16.dp,
    val views_spacing_large: Dp = 24.dp,
    val views_spacing_extra_large: Dp = 32.dp,

    val logo_size: Dp = 250.dp,
    val empty_chat_icon_size: Dp = 175.dp,
    val chat_image_size: Dp = 48.dp,

    val profile_image_size: Dp = 150.dp,
    val profile_gallery_image_size: Dp = 120.dp,
    val profile_gallery_empty_image_size: Dp = 150.dp,
)

val defaultDimensions = Dimensions()
