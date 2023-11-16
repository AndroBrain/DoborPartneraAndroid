package ee.pw.edu.pl.data.model.match.remote

import kotlinx.serialization.Serializable

@Serializable
data class MatchRequest(
    val id: Int,
)
