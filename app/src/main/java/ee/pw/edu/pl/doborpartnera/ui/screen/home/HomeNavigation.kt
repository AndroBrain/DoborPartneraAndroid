package ee.pw.edu.pl.doborpartnera.ui.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable

const val HOME_ROUTE = "HOME"

fun NavGraphBuilder.homeScreen() {
    composable(HOME_ROUTE) {
        HomeScreen()
    }
}

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(HOME_ROUTE, navOptions)
}
