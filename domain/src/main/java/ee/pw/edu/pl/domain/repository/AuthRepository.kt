package ee.pw.edu.pl.domain.repository

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.usecase.auth.login.LoginForm
import ee.pw.edu.pl.domain.usecase.auth.register.RegisterForm
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(form: RegisterForm): UseCaseResult<Unit>
    suspend fun login(form: LoginForm): UseCaseResult<Unit>
    suspend fun getToken(): Flow<String?>
}
