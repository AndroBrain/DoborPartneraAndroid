package ee.pw.edu.pl.data.model.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SetProfileInfoRequest(
    @SerialName("avatarurl")
    val avatarUrl: String,
    val description: String,
)
