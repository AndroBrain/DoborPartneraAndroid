package ee.pw.edu.pl.doborpartnera.ui.screen.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable

const val PROFILE_ROUTE = "PROFILE"

fun NavGraphBuilder.profileScreen() {
    composable(PROFILE_ROUTE) {
        ProfileScreen()
    }
}

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    navigate(PROFILE_ROUTE, navOptions)
}
