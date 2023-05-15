package ee.pw.edu.pl.domain.usecase.auth.register

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUseCase @Inject constructor() {
    operator fun invoke(form: RegisterForm): Flow<UseCaseResult<Unit>> = flow {
        delay(2000L)
        emit(UseCaseResult.Ok(Unit))
//        emit(UseCaseResult.Error(ResultErrorType.EMAIL_TAKEN))
    }
}
