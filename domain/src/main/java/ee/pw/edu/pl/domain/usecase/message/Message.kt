package ee.pw.edu.pl.domain.usecase.message

data class Message(
    val text: String,
    val isYour: Boolean,
)
