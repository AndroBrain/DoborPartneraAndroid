package ee.pw.edu.pl.domain.usecase.profile

import ee.pw.edu.pl.domain.core.result.ResultErrorType
import ee.pw.edu.pl.domain.core.result.UseCaseResult
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetIsProfileFilledUseCase @Inject constructor(

) {
    operator fun invoke(): Flow<UseCaseResult<Boolean>> = flow {
        delay(2000L)
        if (count % 2 == 1) {
            emit(UseCaseResult.Ok(true))
//            emit(UseCaseResult.Ok(false))
            count++
        } else {
            emit(UseCaseResult.Error(ResultErrorType.NETWORK))
            count++
        }
    }

    companion object {
        private var count = 0
    }
}
