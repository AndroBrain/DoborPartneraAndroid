package ee.pw.edu.pl.doborpartnera.ui.screen.chat

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatState(
    val messages: List<MessageDisplayable> = emptyList(),
    val message: String = "",
    val messagesBeingSent: Int = 0,
    @StringRes val errorMsg: Int? = null,
    val name: String = "",
    val imageUrl: String = "",
    val id: Int = -1,
    val canLoadMoreMessages: Boolean = true,
    val isLoadingMoreMessages: Boolean = false,
) : UiState
