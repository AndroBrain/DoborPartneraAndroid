package ee.pw.edu.pl.data.core.remote

import ee.pw.edu.pl.data.model.account.remote.GetAccountInfoResponse
import ee.pw.edu.pl.data.model.account.remote.SetAccountInfoRequest
import ee.pw.edu.pl.data.model.account.remote.SetTestRequest
import ee.pw.edu.pl.data.model.auth.remote.LoginRequest
import ee.pw.edu.pl.data.model.auth.remote.LoginResponse
import ee.pw.edu.pl.data.model.auth.remote.RegisterRequest
import ee.pw.edu.pl.data.model.auth.remote.RegisterResponse
import ee.pw.edu.pl.data.model.match.remote.DeclineMatchRequest
import ee.pw.edu.pl.data.model.match.remote.MatchRequest
import ee.pw.edu.pl.data.model.match.remote.MatchResponse
import ee.pw.edu.pl.data.model.message.remote.LoadMoreMessagesRequest
import ee.pw.edu.pl.data.model.message.remote.LoadMoreMessagesResponse
import ee.pw.edu.pl.data.model.message.remote.ProfileWithMessagesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val PATH_AUTH = "auth"
private const val PATH_ACCOUNT = "account"
private const val PATH_MATCH = "partner"
private const val PATH_MESSAGE = "message"

interface ApiService {
    //    Auth
    @POST("$PATH_AUTH/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("$PATH_AUTH/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    // Account
    @POST("$PATH_ACCOUNT/info")
    suspend fun setInfo(@Body request: SetAccountInfoRequest): Response<Unit>

    @GET("$PATH_ACCOUNT/info")
    suspend fun getInfo(): Response<GetAccountInfoResponse>

    @POST("$PATH_ACCOUNT/test")
    suspend fun setTest(@Body request: SetTestRequest): Response<Unit>

    // Match
    @POST("$PATH_MATCH/decline")
    suspend fun decline(@Body request: DeclineMatchRequest): Response<Unit>

    @GET("$PATH_MATCH/matches")
    suspend fun getMatches(): Response<List<MatchResponse>>

    @POST("$PATH_MATCH/match")
    suspend fun getMatch(@Body request: MatchRequest): Response<MatchResponse>

    // Message
    @GET("$PATH_MESSAGE/conversations")
    suspend fun getConversations(): Response<List<ProfileWithMessagesResponse>>

    @POST("$PATH_MESSAGE/messages")
    suspend fun loadMoreMessages(@Body request: LoadMoreMessagesRequest): Response<LoadMoreMessagesResponse>
}
