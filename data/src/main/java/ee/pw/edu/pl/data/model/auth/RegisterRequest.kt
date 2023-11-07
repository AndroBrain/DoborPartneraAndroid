package ee.pw.edu.pl.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email: String,
    val name: String,
    val surname: String,
    val gender: String,
    val password: String,
)
