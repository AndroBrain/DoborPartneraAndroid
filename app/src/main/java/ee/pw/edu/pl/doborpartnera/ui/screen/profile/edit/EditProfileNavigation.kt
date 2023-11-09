package ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable

const val EDIT_PROFILE_ROUTE = "EDIT_PROFILE"
const val FILL_PROFILE_ROUTE = "FILL_PROFILE"

fun NavGraphBuilder.editProfileScreen(navigateUp: () -> Unit) {
    composable(EDIT_PROFILE_ROUTE) {
        val viewModel: EditProfileViewModel = hiltViewModel()
        EditProfileScreen(
            onSaved = navigateUp,
            viewModel = viewModel,
            title = stringResource(id = R.string.profile_edit),
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                }
            }
        )
    }
}

fun NavGraphBuilder.fillProfileScreen(navigateToHome: () -> Unit) {
    composable(FILL_PROFILE_ROUTE) {
        val viewModel: EditProfileViewModel = hiltViewModel()
        EditProfileScreen(
            onSaved = navigateToHome,
            viewModel = viewModel,
            title = stringResource(id = R.string.profile_fill)
        )
    }
}

fun NavController.navigateToEditProfile(
    navOptions: NavOptions? = null,
) {
    navigate(EDIT_PROFILE_ROUTE, navOptions)
}

fun NavController.navigateToFillProfile(
    navOptions: NavOptions? = null,
) {
    navigate(FILL_PROFILE_ROUTE, navOptions)
}
