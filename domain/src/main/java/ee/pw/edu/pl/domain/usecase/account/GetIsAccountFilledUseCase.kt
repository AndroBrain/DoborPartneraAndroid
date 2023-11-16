package ee.pw.edu.pl.domain.usecase.account

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetIsAccountFilledUseCase @Inject constructor(

) {
    operator fun invoke(): Flow<UseCaseResult<Boolean>> = flow {
        emit(UseCaseResult.Ok(true))
    }
}
