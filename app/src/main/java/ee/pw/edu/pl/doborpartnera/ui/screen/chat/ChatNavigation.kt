package ee.pw.edu.pl.doborpartnera.ui.screen.chat

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import ee.pw.edu.pl.doborpartnera.ui.navigation.NavigationDecoder
import ee.pw.edu.pl.doborpartnera.ui.navigation.NavigationEncoder
import ee.pw.edu.pl.doborpartnera.ui.navigation.composable
import ee.pw.edu.pl.domain.usecase.chat.people.ChatPerson

const val CHAT_ROUTE = "CHAT"
private const val ID_ARG = "ID"
private const val NAME_ARG = "NAME"
private const val IMAGE_URL_ARG = "IMAGE_URL"

fun NavGraphBuilder.chatScreen(
    navigateUp: () -> Unit,
    navigateToProfile: (Long) -> Unit,
) {
    composable("$CHAT_ROUTE/{$ID_ARG}/{$NAME_ARG}/{$IMAGE_URL_ARG}") {
        val viewModel: ChatViewModel = hiltViewModel()
        ChatScreen(
            viewModel = viewModel,
            navigateUp = navigateUp,
            navigateToProfile = navigateToProfile
        )
    }
}

fun NavController.navigateToChat(navOptions: NavOptions? = null, person: ChatPerson) {
    navigate(
        "${CHAT_ROUTE}/${person.id}/${person.name}/${NavigationEncoder.encodeText(person.imageUrl)}",
        navOptions
    )
}

internal class ChatArgs(val id: Long, val name: String, val imageUrl: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        id = (checkNotNull(savedStateHandle[ID_ARG]) as String).toLong(),
        name = checkNotNull(savedStateHandle[NAME_ARG]),
        imageUrl = NavigationDecoder.decodeText(checkNotNull(savedStateHandle[IMAGE_URL_ARG]) as String),
    )
}
