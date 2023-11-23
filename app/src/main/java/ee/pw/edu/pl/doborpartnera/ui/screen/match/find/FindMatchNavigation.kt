package ee.pw.edu.pl.doborpartnera.ui.screen.match.find

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable
import ee.pw.edu.pl.doborpartnera.ui.screen.match.profile.ProfileWithMessagesDisplayable

const val FIND_MATCH_ROUTE = "FIND_MATCH"

fun NavGraphBuilder.findMatchScreen(navigateToChat: (ProfileWithMessagesDisplayable) -> Unit) {
    composable(FIND_MATCH_ROUTE) {
        val viewModel: FindMatchViewModel = hiltViewModel()
        FindMatchScreen(
            viewModel = viewModel,
            navigateToChat = navigateToChat,
        )
    }
}

fun NavController.navigateToFindMatch(navOptions: NavOptions? = null) {
    navigate(FIND_MATCH_ROUTE, navOptions)
}
