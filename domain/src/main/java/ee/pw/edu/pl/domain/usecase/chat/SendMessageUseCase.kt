package ee.pw.edu.pl.domain.usecase.chat

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SendMessageUseCase @Inject constructor(

) {
    operator fun invoke(message: String): Flow<UseCaseResult<String>> = flow {
        delay(2000L)
        emit(UseCaseResult.Ok(message))
    }
}
