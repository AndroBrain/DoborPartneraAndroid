package ee.pw.edu.pl.domain.usecase.message.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatMessage(
    val id: Int,
    val ownerId: Int,
    val text: String,
) : Parcelable
