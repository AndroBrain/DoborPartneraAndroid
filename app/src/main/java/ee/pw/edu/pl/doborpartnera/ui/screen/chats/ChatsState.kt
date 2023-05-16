package ee.pw.edu.pl.doborpartnera.ui.screen.chats

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import ee.pw.edu.pl.domain.usecase.chat.ChatPerson
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatsState(
    val chatPeople: List<ChatPerson>? = null,
    val isInError: Boolean = false,
    @StringRes val errorMsg: Int? = null,
    val isLoading: Boolean = false,
) : UiState
