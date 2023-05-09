package ee.pw.edu.pl.doborpartnera.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import ee.pw.edu.pl.doborpartnera.ui.screen.chat.CHAT_ROUTE
import ee.pw.edu.pl.doborpartnera.ui.screen.chat.chatScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.match.matchScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.profile.profileScreen

@Composable
fun HomeNavGraph(modifier: Modifier = Modifier, navController: NavHostController) {
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = CHAT_ROUTE,
    ) {
        chatScreen()
        matchScreen()
        profileScreen()
    }
}
