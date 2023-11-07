package ee.pw.edu.pl.domain.usecase.auth.register

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.usecase.auth.AuthRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(form: RegisterForm): Flow<UseCaseResult<Unit>> = flow {
        emit(authRepository.register(form))
    }
}
