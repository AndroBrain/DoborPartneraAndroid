package ee.pw.edu.pl.data.model.message.remote

import kotlinx.serialization.Serializable

@Serializable
data class ProfileWithMessagesResponse(
    val avatar: String,
    val id: Int,
    val name: String,
    val messages: List<MessageResponse>,
)
