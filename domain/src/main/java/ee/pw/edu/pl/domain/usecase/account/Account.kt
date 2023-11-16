package ee.pw.edu.pl.domain.usecase.account

data class Account(
    val name: String,
    val surname: String,
    val avatar: String,
    val shortDescription: String,
    val images: List<String>,
    val interests: List<String>,
)
