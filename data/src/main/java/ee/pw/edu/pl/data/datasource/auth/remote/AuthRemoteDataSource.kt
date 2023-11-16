package ee.pw.edu.pl.data.datasource.auth.remote

import ee.pw.edu.pl.data.model.ApiResponseWithHeaders
import ee.pw.edu.pl.data.model.auth.LoginRequest
import ee.pw.edu.pl.data.model.auth.LoginResponse
import ee.pw.edu.pl.data.model.auth.RegisterRequest
import ee.pw.edu.pl.data.model.auth.RegisterResponse

interface AuthRemoteDataSource {
    suspend fun register(request: RegisterRequest): ApiResponseWithHeaders<RegisterResponse>
    suspend fun login(request: LoginRequest): ApiResponseWithHeaders<LoginResponse>
}
