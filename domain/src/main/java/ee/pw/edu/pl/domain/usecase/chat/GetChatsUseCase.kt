package ee.pw.edu.pl.domain.usecase.chat

import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetChatsUseCase @Inject constructor(

) {
    //    TODO replace with real value instead of mock
    operator fun invoke(): Flow<List<Chat>> = flow {
        delay(2000L)
        emit(emptyList())
    }
}
