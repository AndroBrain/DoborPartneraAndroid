package ee.pw.edu.pl.domain.usecase.chat.profile

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoveChatPersonUseCase @Inject constructor(

) {
    operator fun invoke(chatProfile: ChatProfile): Flow<UseCaseResult<ChatProfile>> = flow {
        delay(2000L)
        emit(UseCaseResult.Ok(chatProfile))
    }
}
