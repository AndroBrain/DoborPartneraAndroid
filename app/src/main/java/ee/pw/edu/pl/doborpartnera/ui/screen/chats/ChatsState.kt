package ee.pw.edu.pl.doborpartnera.ui.screen.chats

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.core.viewmodel.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatsState(
    val chatPeople: List<ChatPersonDisplayable>? = null,
    val isInError: Boolean = false,
    @StringRes val errorMsg: Int? = null,
    val isLoading: Boolean = false,
) : UiState
