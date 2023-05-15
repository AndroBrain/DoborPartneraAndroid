package ee.pw.edu.pl.domain.usecase.profile

import ee.pw.edu.pl.domain.core.result.ResultErrorType
import ee.pw.edu.pl.domain.core.result.UseCaseResult
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetProfileUseCase @Inject constructor() {
    suspend operator fun invoke(): Flow<UseCaseResult<Profile>> = flow {
        delay(2000L)
        if (count % 2 == 1) {
            emit(
                UseCaseResult.Ok(
                    Profile(
                        email = "example@gmail.com",
                        name = "name",
                        surname = "surname",
                        imageUrl = "https://cdn.pixabay.com/photo/2017/04/01/21/06/portrait-2194457_960_720.jpg",
                        shortDescription = "Jestem silnym i niezależnym programistą, który poszukuje miłości na dłużej. Jeśli chcesz się ze mną spotkać napisz do mnie :)",
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
