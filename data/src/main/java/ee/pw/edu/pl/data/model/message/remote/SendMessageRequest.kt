package ee.pw.edu.pl.data.model.message.remote

data class SendMessageRequest(
    val receiverId: Int,
    val message: String,
)
