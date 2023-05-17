package ee.pw.edu.pl.doborpartnera.ui.screen.home.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.screen.chats.CHATS_ROUTE
import ee.pw.edu.pl.doborpartnera.ui.screen.match.MATCH_ROUTE
import ee.pw.edu.pl.doborpartnera.ui.screen.profile.PROFILE_ROUTE

enum class HomeBottomNavigationItems(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String,
) {
    CHATS(title = R.string.chats_title, icon = Icons.Default.Chat, route = CHATS_ROUTE),
    MATCH(title = R.string.match_title, icon = Icons.Default.Favorite, route = MATCH_ROUTE),
    PROFILE(
        title = R.string.profile_title,
        icon = Icons.Default.AccountCircle,
        route = PROFILE_ROUTE,
    ),
}
