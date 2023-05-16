package ee.pw.edu.pl.domain.usecase.chat

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SubscribeToChatUseCase @Inject constructor(

) {
    operator fun invoke(): Flow<UseCaseResult<Chat>> = flow {
        while (true) {
            delay(2000L)
            emit(UseCaseResult.Ok(Chat(text = "Siema!", isYour = false)))
        }
    }
}
