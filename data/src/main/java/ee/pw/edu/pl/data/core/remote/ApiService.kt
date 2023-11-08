package ee.pw.edu.pl.data.core.remote

import ee.pw.edu.pl.data.model.auth.LoginRequest
import ee.pw.edu.pl.data.model.auth.LoginResponse
import ee.pw.edu.pl.data.model.auth.RegisterRequest
import ee.pw.edu.pl.data.model.auth.RegisterResponse
import ee.pw.edu.pl.data.model.profile.SetProfileInfoRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

private const val PATH_AUTH = "auth"
private const val PATH_ACCOUNT = "account"

interface ApiService {
    //    Auth
    @POST("$PATH_AUTH/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("$PATH_AUTH/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    // Account
    @POST("$PATH_ACCOUNT/info")
    suspend fun setInfo(@Body request: SetProfileInfoRequest): Response<Unit>
}
