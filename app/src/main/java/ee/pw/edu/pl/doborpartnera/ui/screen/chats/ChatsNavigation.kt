package ee.pw.edu.pl.doborpartnera.ui.screen.chats

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable

const val CHATS_ROUTE = "CHATS"

fun NavGraphBuilder.chatsScreen() {
    composable(CHATS_ROUTE) {
        val viewModel: ChatsViewModel = hiltViewModel()
        ChatScreen(viewModel = viewModel)
    }
}

fun NavController.navigateToChats(navOptions: NavOptions? = null) {
    navigate(CHATS_ROUTE, navOptions)
}
