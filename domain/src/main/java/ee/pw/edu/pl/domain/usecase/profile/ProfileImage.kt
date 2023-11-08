package ee.pw.edu.pl.domain.usecase.profile

data class ProfileImage(
    val order: Int,
    val bytes: ByteArray,
    val format: String,
)
