package ee.pw.edu.pl.data.model.match

import kotlinx.serialization.Serializable

@Serializable
data class DeclineMatchRequest(
    val id: Int,
)
