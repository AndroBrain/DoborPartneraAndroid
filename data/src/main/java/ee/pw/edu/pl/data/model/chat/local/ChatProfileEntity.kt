package ee.pw.edu.pl.data.model.chat.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChatProfileEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val avatar: String,
)
