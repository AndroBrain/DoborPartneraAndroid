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
import ee.pw.edu.pl.doborpartnera.ui.screen.home.HOME_ROUTE
import ee.pw.edu.pl.doborpartnera.ui.screen.home.homeScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.home.navigateToHome
import ee.pw.edu.pl.doborpartnera.ui.screen.match.find.findMatchScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.match.find.navigateToFindMatch
import ee.pw.edu.pl.doborpartnera.ui.screen.match.profile.matchProfileScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.match.profile.navigateToMatchProfile
import ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit.FILL_PROFILE_ROUTE
import ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit.editProfileScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit.fillProfileScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit.navigateToEditProfile
import ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit.navigateToFillProfile
import ee.pw.edu.pl.doborpartnera.ui.screen.test.TEST_ROUTE
import ee.pw.edu.pl.doborpartnera.ui.screen.test.navigateToTestScreen
import ee.pw.edu.pl.doborpartnera.ui.screen.test.testScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier, isLoggedIn: Boolean) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = if (isLoggedIn) HOME_ROUTE else AUTH_ROUTE,
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
            },
            navigateToFillProfile = {
                navController.navigateToFillProfile(
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
            navigateToFillProfile = {
                navController.navigateToFillProfile(
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
        homeScreen(
            navigateToFindMatch = { navController.navigateToFindMatch() },
            navigateToChat = { person -> navController.navigateToChat(person = person) },
            navigateToEditProfile = { args -> navController.navigateToEditProfile(args = args) },
        )
        findMatchScreen(navigateToChat = { person -> navController.navigateToChat(person = person) })
        chatScreen(
            navigateUp = { navController.navigateUp() },
            navigateToProfile = { id -> navController.navigateToMatchProfile(id = id) },
        )
        matchProfileScreen(navigateUp = { navController.navigateUp() })
        editProfileScreen(navigateUp = { navController.navigateUp() })
        testScreen(
            onSent = {
                navController.navigateToHome(
                    navOptions = navOptions {
                        popUpTo(TEST_ROUTE) {
                            inclusive = true
                        }
                    }
                )
            }
        )
        fillProfileScreen(
            onSaved = {
                navController.navigateToTestScreen(
                    navOptions = navOptions {
                        popUpTo(FILL_PROFILE_ROUTE) {
                            inclusive = true
                        }
                    }
                )
            },
        )
    }
}
