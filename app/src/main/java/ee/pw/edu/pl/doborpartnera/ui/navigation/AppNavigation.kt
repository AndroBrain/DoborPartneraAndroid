package ee.pw.edu.pl.doborpartnera.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navOptions
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import ee.pw.edu.pl.doborpartnera.ui.screen.auth.AUTH_ROUTE
import ee.pw.edu.pl.doborpartnera.ui.screen.auth.authScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.auth.login.loginScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.auth.login.navigateToLogin
import ee.pw.edu.pl.doborpartnera.ui.screen.auth.register.navigateToRegister
import ee.pw.edu.pl.doborpartnera.ui.screen.auth.register.registerScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.home.homeScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.home.navigateToHome

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AUTH_ROUTE,
    ) {
        authScreen(
            navigateToLogin = { navController.navigateToLogin() },
            navigateToRegister = { navController.navigateToRegister() },
        )
        loginScreen(
            navigateUp = { navController.navigateUp() },
            navigateToHome = {
                navController.navigateToHome(
                    navOptions = navOptions {
                        popUpTo(
                            navController.graph.findStartDestination().id
                        ) {
                            inclusive = true
                        }
                    }
                )
            }
        )
        registerScreen(navigateUp = { navController.navigateUp() })
        homeScreen()
    }
}
