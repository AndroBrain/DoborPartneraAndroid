package ee.pw.edu.pl.data.model.chat.remote

import kotlinx.serialization.Serializable

@Serializable
data class ChatProfileResponse(
    val avatar: String,
    val id: Int,
    val name: String
)
