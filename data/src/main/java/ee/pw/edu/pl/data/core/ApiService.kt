package ee.pw.edu.pl.data.core

import ee.pw.edu.pl.data.model.auth.RegisterRequest
import ee.pw.edu.pl.data.model.auth.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

private const val PATH_AUTH = "auth"

interface ApiService {
    //    Auth
    @POST("$PATH_AUTH/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}