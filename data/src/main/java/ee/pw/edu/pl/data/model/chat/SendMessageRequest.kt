package ee.pw.edu.pl.data.model.chat

data class SendMessageRequest(
    val receiverId: Int,
    val message: String,
)
