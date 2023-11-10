package ee.pw.edu.pl.data.core.remote

import ee.pw.edu.pl.data.model.auth.LoginRequest
import ee.pw.edu.pl.data.model.auth.LoginResponse
import ee.pw.edu.pl.data.model.auth.RegisterRequest
import ee.pw.edu.pl.data.model.auth.RegisterResponse
import ee.pw.edu.pl.data.model.match.DeclineMatchRequest
import ee.pw.edu.pl.data.model.match.MatchResponse
import ee.pw.edu.pl.data.model.profile.GetProfileInfoResponse
import ee.pw.edu.pl.data.model.profile.SetProfileInfoRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val PATH_AUTH = "auth"
private const val PATH_ACCOUNT = "account"
private const val PATH_MATCH = "partner"

interface ApiService {
    //    Auth
    @POST("$PATH_AUTH/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("$PATH_AUTH/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    // Account
    @POST("$PATH_ACCOUNT/info")
    suspend fun setInfo(@Body request: SetProfileInfoRequest): Response<Unit>

    @GET("$PATH_ACCOUNT/info")
    suspend fun getInfo(): Response<GetProfileInfoResponse>

    // Match
    @POST("$PATH_MATCH/decline")
    suspend fun decline(@Body request: DeclineMatchRequest): Response<Unit>

    @GET("$PATH_MATCH/matches")
    suspend fun getMatches(): Response<List<MatchResponse>>
}
