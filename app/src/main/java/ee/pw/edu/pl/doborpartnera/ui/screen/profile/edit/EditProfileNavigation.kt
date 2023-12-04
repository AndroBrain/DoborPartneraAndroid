package ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.navigation.NavigationEncoder
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

const val EDIT_PROFILE_ROUTE = "EDIT_PROFILE"
const val FILL_PROFILE_ROUTE = "FILL_PROFILE"

private const val AVATAR_ARG = "AVATAR"
private const val IMAGES_ARG = "IMAGES"
private const val DESCRIPTION_ARG = "DESCRIPTION"
private const val INTERESTS_ARG = "INTERESTS"

fun NavGraphBuilder.editProfileScreen(navigateUp: () -> Unit) {
    composable("$EDIT_PROFILE_ROUTE/{$AVATAR_ARG}/{$IMAGES_ARG}/{$DESCRIPTION_ARG}/{$INTERESTS_ARG}") {
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

fun NavGraphBuilder.fillProfileScreen(onSaved: () -> Unit) {
    composable(FILL_PROFILE_ROUTE) {
        val viewModel: EditProfileViewModel = hiltViewModel()
        EditProfileScreen(
            onSaved = onSaved,
            viewModel = viewModel,
            title = stringResource(id = R.string.profile_fill)
        )
    }
}

fun NavController.navigateToEditProfile(
    navOptions: NavOptions? = null,
    args: EditProfileArgs,
) {
    navigate(
        "$EDIT_PROFILE_ROUTE/" +
                "${NavigationEncoder.encodeNullable(args.avatar)}/" +
                "${Json.encodeToString(args.images?.map { NavigationEncoder.encode(it) })}/" +
                "${args.description}/" +
                Json.encodeToString(args.interests),
        navOptions,
    )
}

fun NavController.navigateToFillProfile(
    navOptions: NavOptions? = null,
) {
    navigate(FILL_PROFILE_ROUTE, navOptions)
}

data class EditProfileArgs(
    val avatar: String?,
    val images: List<String>?,
    val description: String?,
    val interests: List<String>?,
) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        avatar = savedStateHandle[AVATAR_ARG],
        images = savedStateHandle.get<String>(IMAGES_ARG)?.let { Json.decodeFromString(it) },
        description = savedStateHandle[DESCRIPTION_ARG],
        interests = savedStateHandle.get<String>(INTERESTS_ARG)?.let { Json.decodeFromString(it) },
    )
}
