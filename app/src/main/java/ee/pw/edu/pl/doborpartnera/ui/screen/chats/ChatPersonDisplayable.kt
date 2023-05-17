package ee.pw.edu.pl.doborpartnera.ui.screen.chats

import android.os.Parcelable
import ee.pw.edu.pl.domain.usecase.chat.people.ChatPerson
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatPersonDisplayable(
    val person: ChatPerson,
    val isLoading: Boolean = false,
) : Parcelable
