package ee.pw.edu.pl.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String,
    val answeredToForm: Boolean,
)
