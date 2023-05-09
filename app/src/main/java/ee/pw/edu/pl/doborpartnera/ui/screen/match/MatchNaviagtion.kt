package ee.pw.edu.pl.doborpartnera.ui.screen.match

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable

const val MATCH_ROUTE = "MATCH"

fun NavGraphBuilder.matchScreen() {
    composable(MATCH_ROUTE) {
        MatchScreen()
    }
}

fun NavController.navigateToMatch(navOptions: NavOptions? = null) {
    navigate(MATCH_ROUTE, navOptions)
}
