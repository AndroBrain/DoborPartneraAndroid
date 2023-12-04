package ee.pw.edu.pl.domain.usecase.account

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import kotlinx.coroutines.delay

class SetTestUseCase {
    suspend operator fun invoke(form: SetTestForm): UseCaseResult<Unit> {
//        TODO implement
        delay(2000L)
        return UseCaseResult.Ok(Unit)
    }
}
