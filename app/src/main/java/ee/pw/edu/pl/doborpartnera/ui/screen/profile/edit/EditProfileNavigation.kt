package ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable

const val EDIT_PROFILE_ROUTE = "EDIT_PROFILE"

fun NavGraphBuilder.editProfileScreen(navigateUp: () -> Unit) {
    composable(EDIT_PROFILE_ROUTE) {
        val viewModel: EditProfileViewModel = hiltViewModel()
        EditProfileScreen(navigateUp = navigateUp, viewModel = viewModel)
    }
}

fun NavController.navigateToEditProfile(
    navOptions: NavOptions? = null,
) {
    navigate(EDIT_PROFILE_ROUTE, navOptions)
}
