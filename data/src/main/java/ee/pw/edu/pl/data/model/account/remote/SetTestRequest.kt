package ee.pw.edu.pl.data.model.account.remote

import kotlinx.serialization.Serializable

@Serializable
data class SetTestRequest(
    val eyes: Int,
    val hair: Int,
    val tattoo: Int,
    val sport: Int,
    val education: Int,
    val recreation: Int,
    val family: Int,
    val charity: Int,
    val people: Int,
    val wedding: Int,
    val belief: Int,
    val money: Int,
    val religious: Int,
    val mind: Int,
    val humour: Int,
)
