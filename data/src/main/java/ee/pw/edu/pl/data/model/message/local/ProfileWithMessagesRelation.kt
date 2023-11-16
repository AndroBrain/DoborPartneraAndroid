package ee.pw.edu.pl.data.model.message.local

import androidx.room.Embedded
import androidx.room.Relation
import ee.pw.edu.pl.data.model.profile.local.ProfileEntity

data class ProfileWithMessagesRelation(
    @Embedded val profile: ProfileEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
    )
    val messages: List<MessageEntity>,
)
