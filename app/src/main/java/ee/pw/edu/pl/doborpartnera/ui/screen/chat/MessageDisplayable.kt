package ee.pw.edu.pl.doborpartnera.ui.screen.chat

import android.os.Parcelable
import ee.pw.edu.pl.domain.usecase.message.Message
import kotlinx.parcelize.Parcelize

@Parcelize
data class MessageDisplayable(
    val text: String,
    val isYour: Boolean,
) : Parcelable {
    constructor(message: Message) : this(
        text = message.text, isYour = message.isYour,
    )
}
