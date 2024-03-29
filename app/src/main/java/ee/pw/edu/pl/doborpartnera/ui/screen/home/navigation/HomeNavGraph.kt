package ee.pw.edu.pl.doborpartnera.ui.screen.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ee.pw.edu.pl.doborpartnera.ui.screen.chats.CHATS_ROUTE
import ee.pw.edu.pl.doborpartnera.ui.screen.chats.chatsScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.chats.navigateToChats
import ee.pw.edu.pl.doborpartnera.ui.screen.match.matchScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.match.profile.ProfileWithMessagesDisplayable
import ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit.EditProfileArgs
import ee.pw.edu.pl.doborpartnera.ui.screen.profile.profileScreen

@Composable
fun HomeNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigateToFindMatch: () -> Unit,
    navigateToEditProfile: (EditProfileArgs) -> Unit,
    navigateToChat: (ProfileWithMessagesDisplayable) -> Unit,
) {
    NavHost(
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
        profileScreen(navigateToEdit = navigateToEditProfile)
    }
}
