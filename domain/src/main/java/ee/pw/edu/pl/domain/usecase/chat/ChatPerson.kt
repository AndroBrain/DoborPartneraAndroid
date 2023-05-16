package ee.pw.edu.pl.domain.usecase.chat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatPerson(
    val name: String,
    val imageUrl: String,
) : Parcelable
