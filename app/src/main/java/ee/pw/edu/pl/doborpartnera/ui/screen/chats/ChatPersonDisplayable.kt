package ee.pw.edu.pl.doborpartnera.ui.screen.chats

import android.os.Parcelable
import ee.pw.edu.pl.domain.usecase.chat.profile.ChatProfile
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatPersonDisplayable(
    val person: ChatProfile,
    val isLoading: Boolean = false,
) : Parcelable
