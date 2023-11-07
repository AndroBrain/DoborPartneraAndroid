package ee.pw.edu.pl.domain.usecase.auth

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.usecase.auth.register.RegisterForm

interface AuthRepository {
    suspend fun register(form: RegisterForm): UseCaseResult<Unit>
}
