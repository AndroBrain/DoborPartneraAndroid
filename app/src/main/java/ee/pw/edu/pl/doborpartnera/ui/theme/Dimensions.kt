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

    val border_width: Dp = 1.dp,
    val icon_button_container_size: Dp = 40.dp,

    val loading_button_indicator_size: Dp = 24.dp,

    val fab_text_padding: Dp = 20.dp,

    val main_icon_size: Dp = 175.dp,

    val logo_size: Dp = 250.dp,
    val chat_delete_loading_indicator_size: Dp = 32.dp,
    val chat_image_size: Dp = 48.dp,
    val chat_message_min_width: Dp = 24.dp,
    val chat_message_vertical_padding: Dp = 4.dp,
    val chat_message_horizontal_padding: Dp = 12.dp,

    val profile_image_size: Dp = 145.dp,
    val profile_gallery_image_size: Dp = 120.dp,
    val profile_gallery_image_width: Dp = 90.dp,
    val profile_gallery_image_height: Dp = 160.dp,

    val find_match_profile_image_size: Dp = 80.dp,

    val answer_width: Dp = 100.dp,
)

val defaultDimensions = Dimensions()
