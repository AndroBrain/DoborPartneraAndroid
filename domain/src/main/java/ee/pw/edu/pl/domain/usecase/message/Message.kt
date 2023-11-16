package ee.pw.edu.pl.domain.usecase.message

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Message(
    val text: String,
    val isYour: Boolean,
) : Parcelable
