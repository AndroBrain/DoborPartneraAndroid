package ee.pw.edu.pl.domain.usecase.match

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchProfile(
    val name: String,
    val age: String,
    val shortDescription: String,
    val profilePhotoUrl: String,
    val galleryImages: List<String>,
) : Parcelable
