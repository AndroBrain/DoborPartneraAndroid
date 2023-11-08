package ee.pw.edu.pl.domain.usecase.profile

data class EditProfileForm(
    val profileAvatar: ProfileAvatar,
    val images: List<ProfileImage>,
    val description: String,
    val interests: List<String>,
)
