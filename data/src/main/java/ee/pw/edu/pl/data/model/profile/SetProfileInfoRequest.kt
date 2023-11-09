package ee.pw.edu.pl.data.model.profile

import kotlinx.serialization.Serializable

@Serializable
data class SetProfileInfoRequest(
    val avatar: String,
    val description: String,
    val interests: List<String>,
    val images: List<String>,
)
