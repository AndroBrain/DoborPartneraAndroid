package ee.pw.edu.pl.data.repository.auth

import ee.pw.edu.pl.data.datasource.auth.AuthRemoteDataSource
import ee.pw.edu.pl.data.model.ApiResponseWithHeaders
import ee.pw.edu.pl.data.model.auth.RegisterRequest
import ee.pw.edu.pl.domain.core.result.ResultErrorType
import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.usecase.auth.AuthRepository
import ee.pw.edu.pl.domain.usecase.auth.register.RegisterForm

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {
    override suspend fun register(form: RegisterForm): UseCaseResult<Unit> {
        val response = authRemoteDataSource.register(
            RegisterRequest(
                email = form.email,
                name = form.name,
                surname = form.surname,
                gender = form.gender.name,
                password = form.password,
            )
        )
        return when (response) {
            is ApiResponseWithHeaders.Error -> UseCaseResult.Error(ResultErrorType.EMAIL_TAKEN)
            is ApiResponseWithHeaders.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponseWithHeaders.Ok -> UseCaseResult.Ok(Unit)
        }
    }
}
