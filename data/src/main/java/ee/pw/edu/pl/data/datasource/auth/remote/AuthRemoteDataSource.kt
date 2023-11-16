package ee.pw.edu.pl.data.datasource.auth.remote

import ee.pw.edu.pl.data.model.ApiResponse
import ee.pw.edu.pl.data.model.auth.remote.LoginRequest
import ee.pw.edu.pl.data.model.auth.remote.LoginResponse
import ee.pw.edu.pl.data.model.auth.remote.RegisterRequest
import ee.pw.edu.pl.data.model.auth.remote.RegisterResponse

interface AuthRemoteDataSource {
    suspend fun register(request: RegisterRequest): ApiResponse<RegisterResponse>
    suspend fun login(request: LoginRequest): ApiResponse<LoginResponse>
}
