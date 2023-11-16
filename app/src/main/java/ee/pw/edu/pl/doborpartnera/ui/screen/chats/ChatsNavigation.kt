package ee.pw.edu.pl.doborpartnera.ui.screen.chats

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable
import ee.pw.edu.pl.domain.usecase.message.profile.ProfileWithMessages

const val CHATS_ROUTE = "CHATS"

fun NavGraphBuilder.chatsScreen(navigateToChat: (ProfileWithMessages) -> Unit) {
    composable(CHATS_ROUTE) {
        val viewModel: ChatsViewModel = hiltViewModel()
        ChatsScreen(viewModel = viewModel, navigateToChat = navigateToChat)
    }
}

fun NavController.navigateToChats(navOptions: NavOptions? = null) {
    navigate(CHATS_ROUTE, navOptions)
}
