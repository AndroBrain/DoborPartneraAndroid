package ee.pw.edu.pl.data.datasource.auth.remote

import ee.pw.edu.pl.data.core.remote.ApiService
import ee.pw.edu.pl.data.model.apiCall
import ee.pw.edu.pl.data.model.auth.LoginRequest
import ee.pw.edu.pl.data.model.auth.RegisterRequest

class RetrofitAuthRemoteDataSource(
    private val api: ApiService,
) : AuthRemoteDataSource {
    override suspend fun register(request: RegisterRequest) = apiCall {
        api.register(request)
    }

    override suspend fun login(request: LoginRequest) = apiCall {
        api.login(request)
    }
}
