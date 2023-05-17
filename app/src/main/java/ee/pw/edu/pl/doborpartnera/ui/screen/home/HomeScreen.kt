package ee.pw.edu.pl.doborpartnera.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import ee.pw.edu.pl.doborpartnera.ui.screen.home.navigation.HomeNavGraph
import ee.pw.edu.pl.doborpartnera.ui.screen.home.navigation.HomeNavigationBar
import ee.pw.edu.pl.domain.usecase.chat.people.ChatPerson

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToFindMatch: () -> Unit,
    navigateToChat: (ChatPerson) -> Unit,
) {
    val navController = rememberAnimatedNavController()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            HomeNavigationBar(navController = navController)
        }
    ) { insets ->
        HomeNavGraph(
            modifier = Modifier.padding(insets),
            navController = navController,
            navigateToFindMatch = navigateToFindMatch,
            navigateToChat = navigateToChat,
        )
    }
}
