package ee.pw.edu.pl.domain.usecase.chat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chat(
    val text: String,
    val isYour: Boolean,
) : Parcelable
