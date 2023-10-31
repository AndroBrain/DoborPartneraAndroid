package ee.pw.edu.pl.doborpartnera.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import ee.pw.edu.pl.doborpartnera.ui.screen.auth.AUTH_ROUTE
import ee.pw.edu.pl.doborpartnera.ui.screen.auth.authScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.auth.login.loginScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.auth.login.navigateToLogin
import ee.pw.edu.pl.doborpartnera.ui.screen.auth.register.navigateToRegister
import ee.pw.edu.pl.doborpartnera.ui.screen.auth.register.registerScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.chat.chatScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.chat.navigateToChat
import ee.pw.edu.pl.doborpartnera.ui.screen.home.homeScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.home.navigateToHome
import ee.pw.edu.pl.doborpartnera.ui.screen.match.find.findMatchScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.match.find.navigateToFindMatch
import ee.pw.edu.pl.doborpartnera.ui.screen.match.profile.matchProfileScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.match.profile.navigateToMatchProfile

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
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
        registerScreen(
            navigateUp = { navController.navigateUp() },
            navigateToLogin = {
                navController.navigateToLogin(
                    navOptions = navOptions {
                        popUpTo(AUTH_ROUTE)
                    }
                )
            }
        )
        homeScreen(
            navigateToFindMatch = { navController.navigateToFindMatch() },
            navigateToChat = { person -> navController.navigateToChat(person = person) },
        )
        findMatchScreen(navigateToChat = { person -> navController.navigateToChat(person = person) })
        chatScreen(
            navigateUp = { navController.navigateUp() },
            navigateToProfile = { id -> navController.navigateToMatchProfile(id = id) },
        )
        matchProfileScreen(navigateUp = { navController.navigateUp() })
    }
}
