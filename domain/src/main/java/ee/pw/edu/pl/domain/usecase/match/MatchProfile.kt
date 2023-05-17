package ee.pw.edu.pl.domain.usecase.match

import android.os.Parcelable
import kotlin.random.Random
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchProfile(
//    TODO remove default value when adding backend
    val id: Long = Random.nextLong(),
    val name: String,
    val age: String,
    val shortDescription: String,
    val profilePhotoUrl: String,
    val galleryImages: List<String>,
) : Parcelable
