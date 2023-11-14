package ee.pw.edu.pl.data.model.chat.local

import androidx.room.Embedded
import androidx.room.Relation

data class ChatProfileWithMessages(
    @Embedded val profile: ChatProfileEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
    )
    val messages: List<MessageEntity>,
)
