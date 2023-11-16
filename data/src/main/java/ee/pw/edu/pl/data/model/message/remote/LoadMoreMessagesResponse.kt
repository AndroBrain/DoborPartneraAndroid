package ee.pw.edu.pl.data.model.message.remote

import kotlinx.serialization.Serializable

@Serializable
data class LoadMoreMessagesResponse(
    val messages: List<MessageResponse>,
    val canLoadMore: Boolean,
)
