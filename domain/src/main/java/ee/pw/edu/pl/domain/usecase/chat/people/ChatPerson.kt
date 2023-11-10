package ee.pw.edu.pl.domain.usecase.chat.people

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class ChatPerson(
//    TODO remove default value when adding backend
    val id: Int = Random.nextInt(),
    val name: String,
    val imageUrl: String,
) : Parcelable
