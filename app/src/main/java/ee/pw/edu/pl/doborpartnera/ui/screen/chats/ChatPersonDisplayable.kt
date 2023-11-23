package ee.pw.edu.pl.doborpartnera.ui.screen.chats

import android.os.Parcelable
import ee.pw.edu.pl.doborpartnera.ui.screen.match.profile.ProfileWithMessagesDisplayable
import ee.pw.edu.pl.domain.usecase.message.profile.ProfileWithMessages
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatPersonDisplayable(
    val person: ProfileWithMessagesDisplayable,
    val isLoading: Boolean = false,
) : Parcelable {
    constructor(profileWithMessages: ProfileWithMessages, isLoading: Boolean = false) : this(
        person = ProfileWithMessagesDisplayable(profileWithMessages),
        isLoading = isLoading,
    )
}
