package ee.pw.edu.pl.doborpartnera.ui.screen.profile

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable
import ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit.EditProfileArgs

const val PROFILE_ROUTE = "PROFILE"

fun NavGraphBuilder.profileScreen(navigateToEdit: (EditProfileArgs) -> Unit) {
    composable(PROFILE_ROUTE) {
        val viewModel: ProfileViewModel = hiltViewModel()
        ProfileScreen(navigateToEdit = navigateToEdit, viewModel = viewModel)
    }
}
