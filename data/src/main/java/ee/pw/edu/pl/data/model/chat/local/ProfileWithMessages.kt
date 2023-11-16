package ee.pw.edu.pl.data.model.chat.local

import androidx.room.Embedded
import androidx.room.Relation
import ee.pw.edu.pl.data.model.profile.local.ProfileEntity

data class ProfileWithMessages(
    @Embedded val profile: ProfileEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
    )
    val messages: List<MessageEntity>,
)
