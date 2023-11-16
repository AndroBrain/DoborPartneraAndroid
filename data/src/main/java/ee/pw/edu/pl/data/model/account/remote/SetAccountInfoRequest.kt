package ee.pw.edu.pl.data.model.account.remote

import kotlinx.serialization.Serializable

@Serializable
data class SetAccountInfoRequest(
    val avatar: String,
    val description: String,
    val interests: List<String>,
    val images: List<String>,
)
