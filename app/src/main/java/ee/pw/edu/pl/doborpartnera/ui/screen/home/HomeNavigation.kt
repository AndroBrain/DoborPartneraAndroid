package ee.pw.edu.pl.doborpartnera.ui.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable
import ee.pw.edu.pl.domain.usecase.chat.people.ChatPerson

const val HOME_ROUTE = "HOME"

fun NavGraphBuilder.homeScreen(
    navigateToFindMatch: () -> Unit,
    navigateToChat: (ChatPerson) -> Unit,
) {
    composable(HOME_ROUTE) {
        HomeScreen(navigateToFindMatch = navigateToFindMatch, navigateToChat = navigateToChat)
    }
}

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(HOME_ROUTE, navOptions)
}
