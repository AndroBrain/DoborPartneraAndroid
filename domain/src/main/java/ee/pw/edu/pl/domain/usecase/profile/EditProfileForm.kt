package ee.pw.edu.pl.domain.usecase.profile

data class EditProfileForm(
    val name: String,
    val surname: String,
    val description: String,
)
