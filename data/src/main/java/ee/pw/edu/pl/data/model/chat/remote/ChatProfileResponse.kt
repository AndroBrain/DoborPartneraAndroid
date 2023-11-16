package ee.pw.edu.pl.data.model.chat.remote

import ee.pw.edu.pl.data.model.message.remote.MessageResponse
import kotlinx.serialization.Serializable

@Serializable
data class ChatProfileResponse(
    val avatar: String,
    val id: Int,
    val name: String,
    val messages: List<MessageResponse>,
)
