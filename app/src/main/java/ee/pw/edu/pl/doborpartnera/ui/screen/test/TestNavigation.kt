package ee.pw.edu.pl.doborpartnera.ui.screen.test

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable

const val TEST_ROUTE = "TEST"

fun NavGraphBuilder.testScreen(
    onSent: () -> Unit,
) {
    composable(TEST_ROUTE) {
        val viewModel: TestViewModel = hiltViewModel()
        TestScreen(viewModel = viewModel, onSent = onSent)
    }
}

fun NavController.navigateToTestScreen(
    navOptions: NavOptions? = null,
) {
    navigate(TEST_ROUTE, navOptions)
}
