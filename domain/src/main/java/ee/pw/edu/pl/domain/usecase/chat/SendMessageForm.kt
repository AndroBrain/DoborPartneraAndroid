package ee.pw.edu.pl.domain.usecase.chat

data class SendMessageForm(
    val receiverId: Int,
    val message: String,
)
