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

    val letter_box_size: Dp = 48.dp,

    val keyboard_letter_vertical_spacing: Dp = 8.dp,
    val dialog_icon_size: Dp = 48.dp,

    val game_history_height: Dp = 32.dp,
)

val defaultDimensions = Dimensions()
