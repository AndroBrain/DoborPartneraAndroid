package ee.pw.edu.pl.data.model.profile.remote

import kotlinx.serialization.Serializable

@Serializable
data class GetProfileInfoResponse(
    val name: String,
    val surname: String,
    val avatar: String,
    val description: String,
    val images: List<String>,
    val interests: List<String>,
)
