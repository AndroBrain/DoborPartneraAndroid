package ee.pw.edu.pl.data.model.match

import ee.pw.edu.pl.data.core.serializer.DateSerializer
import java.util.*
import kotlinx.serialization.Serializable

@Serializable
data class MatchResponse(
    val userId: Int,
    val name: String,
    @Serializable(with = DateSerializer::class)
    val birthdate: Date,
    val description: String,
    val avatar: String,
    val images: List<String>,
    val interests: List<String>,
)
