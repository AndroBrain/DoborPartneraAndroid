package ee.pw.edu.pl.doborpartnera.ui.screen.test

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable

const val TEST_ROUTE = "TEST"

fun NavGraphBuilder.testScreen() {
    composable(TEST_ROUTE) {
        val viewModel: TestViewModel = hiltViewModel()
        TestScreen(viewModel = viewModel)
    }
}
