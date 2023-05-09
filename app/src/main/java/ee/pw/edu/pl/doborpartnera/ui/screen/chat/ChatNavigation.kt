package ee.pw.edu.pl.doborpartnera.ui.screen.chat

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable

const val CHAT_ROUTE = "CHAT"

fun NavGraphBuilder.chatScreen() {
    composable(CHAT_ROUTE) {
        val viewModel: ChatViewModel = hiltViewModel()
        ChatScreen(viewModel = viewModel)
    }
}

fun NavController.navigateToChat(navOptions: NavOptions? = null) {
    navigate(CHAT_ROUTE, navOptions)
}
