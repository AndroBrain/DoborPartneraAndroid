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
        delay(2000L)
        emit(
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
    }
}
