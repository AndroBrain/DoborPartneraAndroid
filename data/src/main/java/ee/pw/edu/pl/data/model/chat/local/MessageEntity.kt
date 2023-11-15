package ee.pw.edu.pl.data.model.chat.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MessageEntity(
    @PrimaryKey val id: Int,
    val ownerId: Int,
    val text: String,
    val timestamp: Long,
)
