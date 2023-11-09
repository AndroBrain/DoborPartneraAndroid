package ee.pw.edu.pl.domain.usecase.profile

data class Profile(
    val name: String,
    val surname: String,
    val avatar: String,
    val shortDescription: String,
    val images: List<String>,
    val interests: List<String>,
)
