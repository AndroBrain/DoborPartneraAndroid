package ee.pw.edu.pl.domain.usecase.chat

import ee.pw.edu.pl.domain.core.result.ResultErrorType
import ee.pw.edu.pl.domain.core.result.UseCaseResult
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetChatsUseCase @Inject constructor(

) {
    //    TODO replace with real value instead of mock
    operator fun invoke(): Flow<UseCaseResult<List<Chat>>> = flow {
        delay(2000L)
        if (count % 2 == 1) {
            emit(UseCaseResult.Ok(emptyList()))
            delay(2000L)
            emit(
                UseCaseResult.Ok(
                    listOf(
                        Chat(
                            name = "Ania Spysińska",
                            imageUrl = "https://cdn.pixabay.com/photo/2017/01/03/09/18/woman-1948939_960_720.jpg"
                        ),
                        Chat(
                            name = "Agnieszka Nowak",
                            imageUrl = "https://cdn.pixabay.com/photo/2016/12/23/22/19/photoshoot-1928086_960_720.jpg"
                        ),
                        Chat(
                            name = "Baśka Kowalska",
                            imageUrl = "https://cdn.pixabay.com/photo/2017/03/23/20/57/girl-2169467_960_720.jpg"
                        ),
                    )
                )
            )
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
