package ee.pw.edu.pl.data.datasource.auth

import ee.pw.edu.pl.data.model.ApiResponseWithHeaders
import ee.pw.edu.pl.data.model.auth.RegisterRequest
import ee.pw.edu.pl.data.model.auth.RegisterResponse

interface AuthRemoteDataSource {
    suspend fun register(request: RegisterRequest): ApiResponseWithHeaders<RegisterResponse>
}
