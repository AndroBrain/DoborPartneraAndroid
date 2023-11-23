package ee.pw.edu.pl.doborpartnera.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ee.pw.edu.pl.doborpartnera.ui.screen.home.navigation.HomeNavGraph
import ee.pw.edu.pl.doborpartnera.ui.screen.home.navigation.HomeNavigationBar
import ee.pw.edu.pl.doborpartnera.ui.screen.match.profile.ProfileWithMessagesDisplayable
import ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit.EditProfileArgs

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToFindMatch: () -> Unit,
    navigateToEditProfile: (EditProfileArgs) -> Unit,
    navigateToChat: (ProfileWithMessagesDisplayable) -> Unit,
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            HomeNavigationBar(navController = navController)
        }
    ) { insets ->
        HomeNavGraph(
            modifier = Modifier.padding(insets),
            navController = navController,
            navigateToFindMatch = navigateToFindMatch,
            navigateToEditProfile = navigateToEditProfile,
            navigateToChat = navigateToChat,
        )
    }
}
