package ee.pw.edu.pl.doborpartnera.ui.screen.match

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable

const val MATCH_ROUTE = "MATCH"

fun NavGraphBuilder.matchScreen(
    navigateToChats: () -> Unit,
    navigateToFindMatch: () -> Unit,
) {
    composable(MATCH_ROUTE) {
        val viewModel: MatchViewModel = hiltViewModel()
        MatchScreen(
            viewModel = viewModel,
            navigateToChats = navigateToChats,
            navigateToFindMatch = navigateToFindMatch,
        )
    }
}

fun NavController.navigateToMatch(navOptions: NavOptions? = null) {
    navigate(MATCH_ROUTE, navOptions)
}
