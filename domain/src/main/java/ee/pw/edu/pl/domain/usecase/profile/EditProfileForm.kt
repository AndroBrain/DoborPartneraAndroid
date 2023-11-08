package ee.pw.edu.pl.domain.usecase.profile

data class EditProfileForm(
    val profileImage: ByteArray,
    val images: List<ByteArray>,
    val description: String,
    val interests: List<String>,
)
