package ee.pw.edu.pl.doborpartnera.ui.screen.match.profile

import android.os.Parcelable
import ee.pw.edu.pl.doborpartnera.ui.screen.chat.MessageDisplayable
import ee.pw.edu.pl.domain.usecase.message.profile.ProfileWithMessages
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileWithMessagesDisplayable(
    val id: Int,
    val name: String,
    val avatar: String,
    val messages: List<MessageDisplayable>,
) : Parcelable {
    constructor(profileWithMessages: ProfileWithMessages) : this(
        id = profileWithMessages.id,
        name = profileWithMessages.name,
        avatar = profileWithMessages.avatar,
        messages = profileWithMessages.messages.map(::MessageDisplayable)
    )
}
