package ee.pw.edu.pl.data.model.profile.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProfileEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val avatar: String,
)
