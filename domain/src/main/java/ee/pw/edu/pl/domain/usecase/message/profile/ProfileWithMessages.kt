package ee.pw.edu.pl.domain.usecase.message.profile

import android.os.Parcelable
import ee.pw.edu.pl.domain.usecase.message.Message
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileWithMessages(
    val id: Int,
    val name: String,
    val avatar: String,
    val messages: List<Message>,
) : Parcelable
