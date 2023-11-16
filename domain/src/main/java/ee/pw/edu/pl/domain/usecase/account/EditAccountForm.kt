package ee.pw.edu.pl.domain.usecase.account

import ee.pw.edu.pl.domain.usecase.profile.ProfileAvatar
import ee.pw.edu.pl.domain.usecase.profile.ProfileImage

data class EditAccountForm(
    val profileAvatar: ProfileAvatar,
    val images: List<ProfileImage>,
    val description: String,
    val interests: List<String>,
)
