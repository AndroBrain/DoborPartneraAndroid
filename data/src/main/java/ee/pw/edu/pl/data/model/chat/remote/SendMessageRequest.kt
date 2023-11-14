package ee.pw.edu.pl.data.model.chat.remote

data class SendMessageRequest(
    val receiverId: Int,
    val message: String,
)
