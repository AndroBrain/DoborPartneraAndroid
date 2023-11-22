package ee.pw.edu.pl.domain.usecase.account

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetIsAccountFilledUseCase {
    operator fun invoke(): Flow<UseCaseResult<Boolean>> = flow {
        emit(UseCaseResult.Ok(true))
    }
}
