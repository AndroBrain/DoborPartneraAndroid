package ee.pw.edu.pl.doborpartnera.ui.screen.chat

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import ee.pw.edu.pl.domain.usecase.chat.Chat
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatState(
    val chats: List<Chat> = emptyList(),
    val message: String = "",
    val messagesBeingSent: Int = 0,
    @StringRes val errorMsg: Int? = null,
    val name: String = "",
    val imageUrl: String = "",
    val id: Int = -1,
) : UiState
