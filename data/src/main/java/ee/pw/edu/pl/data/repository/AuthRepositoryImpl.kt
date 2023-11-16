package ee.pw.edu.pl.data.repository

import ee.pw.edu.pl.data.datasource.auth.local.AuthLocalDataSource
import ee.pw.edu.pl.data.datasource.auth.remote.AuthRemoteDataSource
import ee.pw.edu.pl.data.model.ApiResponse
import ee.pw.edu.pl.data.model.auth.LoginRequest
import ee.pw.edu.pl.data.model.auth.RegisterRequest
import ee.pw.edu.pl.domain.core.result.ResultErrorType
import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.AuthRepository
import ee.pw.edu.pl.domain.usecase.auth.login.LoginForm
import ee.pw.edu.pl.domain.usecase.auth.register.RegisterForm
import java.util.*

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
) : AuthRepository {
    override suspend fun register(form: RegisterForm): UseCaseResult<Unit> {
        val response = authRemoteDataSource.register(
            RegisterRequest(
                email = form.email,
                name = form.name,
                surname = form.surname,
                gender = form.gender.name,
                birthdate = Date(form.birthdate),
                password = form.password,
            )
        )
        return when (response) {
            is ApiResponse.Error -> UseCaseResult.Error(ResultErrorType.EMAIL_TAKEN)
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> {
                authLocalDataSource.setToken(response.body.token)
                UseCaseResult.Ok(Unit)
            }
        }
    }

    override suspend fun login(form: LoginForm): UseCaseResult<Boolean> {
        val response = authRemoteDataSource.login(
            LoginRequest(email = form.email, password = form.password)
        )
        return when (response) {
            is ApiResponse.Error -> UseCaseResult.Error(ResultErrorType.INVALID_CREDENTIALS)
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> {
                authLocalDataSource.setToken(response.body.token)
                UseCaseResult.Ok(response.body.isProfileFilled)
            }
        }
    }

    override suspend fun getToken() = authLocalDataSource.getToken()
}
