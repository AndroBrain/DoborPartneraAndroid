package ee.pw.edu.pl.domain.usecase.chat.people

import android.os.Parcelable
import kotlin.random.Random
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatPerson(
//    TODO remove default value when adding backend
    val id: Long = Random.nextLong(),
    val name: String,
    val imageUrl: String,
) : Parcelable
