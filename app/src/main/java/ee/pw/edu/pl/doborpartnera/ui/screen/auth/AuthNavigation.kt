package ee.pw.edu.pl.doborpartnera.ui.screen.auth

import androidx.navigation.NavGraphBuilder
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable

const val AUTH_ROUTE = "AUTH"

fun NavGraphBuilder.authScreen(navigateToLogin: () -> Unit, navigateToRegister: () -> Unit) {
    composable(AUTH_ROUTE) {
        AuthScreen(
            navigateToLogin = navigateToLogin,
            navigateToRegister = navigateToRegister,
        )
    }
}
