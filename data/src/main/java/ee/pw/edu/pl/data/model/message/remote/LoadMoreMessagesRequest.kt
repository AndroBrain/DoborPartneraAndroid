package ee.pw.edu.pl.data.model.message.remote

import kotlinx.serialization.Serializable

@Serializable
data class LoadMoreMessagesRequest(
    val id: Int,
    val lastMessageTimestamp: Long
)
