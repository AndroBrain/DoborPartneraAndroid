package ee.pw.edu.pl.doborpartnera.ui.screen.chat

import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import ee.pw.edu.pl.domain.usecase.chat.Chat
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatState(
    val chats: List<Chat>? = null,
) : UiState
