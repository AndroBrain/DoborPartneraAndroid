package ee.pw.edu.pl.domain.usecase.match

data class MatchProfile(
    val id: Int,
    val name: String,
    val age: String,
    val description: String,
    val avatar: String,
    val images: List<String>,
    val interests: List<String>,
)
