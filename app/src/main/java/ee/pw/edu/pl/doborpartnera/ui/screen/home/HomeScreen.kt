package ee.pw.edu.pl.doborpartnera.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import ee.pw.edu.pl.doborpartnera.ui.screen.home.navigation.HomeNavigationBar

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val navController = rememberAnimatedNavController()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            HomeNavigationBar(navController = navController)
        }
    ) { insets ->
        HomeNavGraph(modifier = Modifier.padding(insets), navController = navController)
    }
}
