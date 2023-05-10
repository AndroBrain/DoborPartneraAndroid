package ee.pw.edu.pl.domain.usecase.profile

data class Profile(
    val email: String,
    val name: String,
    val surname: String,
    val imageUrl: String,
    val shortDescription: String,
)
