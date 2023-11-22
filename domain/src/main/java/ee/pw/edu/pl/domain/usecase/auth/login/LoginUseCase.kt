package ee.pw.edu.pl.domain.usecase.auth.login

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(form: LoginForm): Flow<UseCaseResult<Boolean>> = flow {
        emit(authRepository.login(form))
    }
}
