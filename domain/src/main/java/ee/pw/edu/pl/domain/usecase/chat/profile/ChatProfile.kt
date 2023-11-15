package ee.pw.edu.pl.domain.usecase.chat.profile

import android.os.Parcelable
import ee.pw.edu.pl.domain.usecase.chat.Chat
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatProfile(
    val id: Int,
    val name: String,
    val avatar: String,
    val messages: List<Chat>,
) : Parcelable
