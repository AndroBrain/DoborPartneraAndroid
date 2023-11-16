package ee.pw.edu.pl.data.model.auth.remote

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val token: String,
)
