package ee.pw.edu.pl.doborpartnera.ui.screen.chats

import android.os.Parcelable
import ee.pw.edu.pl.domain.usecase.message.profile.ProfileWithMessages
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatPersonDisplayable(
    val person: ProfileWithMessages,
    val isLoading: Boolean = false,
) : Parcelable
