package ee.pw.edu.pl.doborpartnera.ui.screen.match.profile

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable

const val MATCH_PROFILE_ROUTE = "MATCH_PROFILE"
private const val ID_ARG = "ID"

fun NavGraphBuilder.matchProfileScreen(
    navigateUp: () -> Unit,
) {
    composable("$MATCH_PROFILE_ROUTE/{$ID_ARG}") {
        val viewModel: MatchProfileViewModel = hiltViewModel()
        MatchProfileScreen(
            navigateUp = navigateUp,
            viewModel = viewModel,
        )
    }
}

fun NavController.navigateToMatchProfile(navOptions: NavOptions? = null, id: Long) {
    navigate(
        "$MATCH_PROFILE_ROUTE/$id",
        navOptions
    )
}

internal class MatchProfileArgs(val id: Long) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        id = (checkNotNull(savedStateHandle[ID_ARG]) as String).toLong(),
    )
}
