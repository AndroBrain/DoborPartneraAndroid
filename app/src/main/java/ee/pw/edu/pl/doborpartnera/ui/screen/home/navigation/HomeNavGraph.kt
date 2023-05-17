package ee.pw.edu.pl.doborpartnera.ui.screen.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import ee.pw.edu.pl.doborpartnera.ui.screen.chats.CHATS_ROUTE
import ee.pw.edu.pl.doborpartnera.ui.screen.chats.chatsScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.chats.navigateToChats
import ee.pw.edu.pl.doborpartnera.ui.screen.match.matchScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.profile.profileScreen
import ee.pw.edu.pl.domain.usecase.chat.people.ChatPerson

@Composable
fun HomeNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigateToFindMatch: () -> Unit,
    navigateToChat: (ChatPerson) -> Unit,
) {
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = CHATS_ROUTE,
    ) {
        chatsScreen(navigateToChat = navigateToChat)
        matchScreen(
            navigateToChats = {
                navController.navigateToChats()
            },
            navigateToFindMatch = navigateToFindMatch,
        )
        profileScreen()
    }
}
