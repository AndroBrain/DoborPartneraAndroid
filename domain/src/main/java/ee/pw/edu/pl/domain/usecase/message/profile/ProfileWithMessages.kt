package ee.pw.edu.pl.domain.usecase.message.profile

import ee.pw.edu.pl.domain.usecase.message.Message

data class ProfileWithMessages(
    val id: Int,
    val name: String,
    val avatar: String,
    val messages: List<Message>,
)
