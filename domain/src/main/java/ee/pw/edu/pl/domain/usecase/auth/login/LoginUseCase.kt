package ee.pw.edu.pl.domain.usecase.auth.login

import ee.pw.edu.pl.domain.core.result.LoginResponse
import ee.pw.edu.pl.domain.core.result.UseCaseResult
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase @Inject constructor() {
    operator fun invoke(form: LoginForm): Flow<UseCaseResult<LoginResponse>> = flow {
        delay(2000L)
        emit(UseCaseResult.Ok(LoginResponse(token = "secret token", isTestFilled = false)))
//        emit(UseCaseResult.Error(ResultErrorType.INVALID_CREDENTIALS))
    }
}
