package ee.pw.edu.pl.data.model.chat.remote

import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    val id: Int,
    val fromUser: Int,
    val toUser: Int,
    val messageText: String,
    val sentTimestamp: Long,
)
