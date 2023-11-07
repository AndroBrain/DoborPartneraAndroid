package ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable
import ee.pw.edu.pl.domain.usecase.profile.EditProfileForm

const val EDIT_PROFILE_ROUTE = "EDIT_PROFILE"
private const val DESCRIPTION_ARG = "DESCRIPTION"

fun NavGraphBuilder.editProfileScreen(navigateUp: () -> Unit) {
    composable("$EDIT_PROFILE_ROUTE/{$DESCRIPTION_ARG}") {
        val viewModel: EditProfileViewModel = hiltViewModel()
        EditProfileScreen(navigateUp = navigateUp, viewModel = viewModel)
    }
}

fun NavController.navigateToEditProfile(
    navOptions: NavOptions? = null,
    editProfile: EditProfileForm
) {
    navigate(
        "$EDIT_PROFILE_ROUTE/${editProfile.description}",
        navOptions,
    )
}

internal class EditProfileArgs(val editProfile: EditProfileForm) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        EditProfileForm(
            description = checkNotNull(savedStateHandle[DESCRIPTION_ARG]),
        )
    )
}
