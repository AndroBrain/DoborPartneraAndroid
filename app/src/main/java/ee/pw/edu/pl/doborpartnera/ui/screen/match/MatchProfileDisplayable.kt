package ee.pw.edu.pl.doborpartnera.ui.screen.match

import android.os.Parcelable
import ee.pw.edu.pl.domain.usecase.match.MatchProfile
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchProfileDisplayable(
    val id: Int,
    val name: String,
    val age: String,
    val description: String,
    val avatar: String,
    val images: List<String>,
    val interests: List<String>,
) : Parcelable {
    constructor(matchProfile: MatchProfile) : this(
        id = matchProfile.id,
        name = matchProfile.name,
        age = matchProfile.age,
        description = matchProfile.description,
        avatar = matchProfile.avatar,
        images = matchProfile.images,
        interests = matchProfile.interests
    )
}
