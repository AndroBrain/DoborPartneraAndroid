package ee.pw.edu.pl.data.model.auth.remote

import ee.pw.edu.pl.data.core.serializer.DateSerializer
import java.util.*
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email: String,
    val name: String,
    val surname: String,
    val gender: String,
    @Serializable(with = DateSerializer::class)
    val birthdate: Date,
    val password: String,
)
