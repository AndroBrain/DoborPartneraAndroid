package ee.pw.edu.pl.doborpartnera.ui.screen.auth.login

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable

const val LOGIN_ROUTE = "LOGIN"

fun NavGraphBuilder.loginScreen(navigateUp: () -> Unit) {
    composable(LOGIN_ROUTE) {
        val viewModel: LoginViewModel = hiltViewModel()
        LoginScreen(viewModel = viewModel, navigateUp = navigateUp)
    }
}

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    navigate(LOGIN_ROUTE, navOptions)
}
