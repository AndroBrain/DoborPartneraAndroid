package ee.pw.edu.pl.doborpartnera.ui.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable
import ee.pw.edu.pl.doborpartnera.ui.screen.match.profile.ProfileWithMessagesDisplayable
import ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit.EditProfileArgs

const val HOME_ROUTE = "HOME"

fun NavGraphBuilder.homeScreen(
    navigateToFindMatch: () -> Unit,
    navigateToEditProfile: (EditProfileArgs) -> Unit,
    navigateToChat: (ProfileWithMessagesDisplayable) -> Unit,
) {
    composable(HOME_ROUTE) {
        HomeScreen(
            navigateToFindMatch = navigateToFindMatch,
            navigateToEditProfile = navigateToEditProfile,
            navigateToChat = navigateToChat,
        )
    }
}

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(HOME_ROUTE, navOptions)
}
