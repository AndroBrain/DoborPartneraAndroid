package ee.pw.edu.pl.doborpartnera.ui.screen.auth.register

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable

const val REGISTER_ROUTE = "REGISTER"

fun NavGraphBuilder.registerScreen(navigateUp: () -> Unit, navigateToLogin: () -> Unit) {
    composable(REGISTER_ROUTE) {
        val viewModel: RegisterViewModel = hiltViewModel()
        RegisterScreen(
            viewModel = viewModel,
            navigateUp = navigateUp,
            navigateToLogin = navigateToLogin,
        )
    }
}

fun NavController.navigateToRegister(navOptions: NavOptions? = null) {
    navigate(REGISTER_ROUTE, navOptions)
}
