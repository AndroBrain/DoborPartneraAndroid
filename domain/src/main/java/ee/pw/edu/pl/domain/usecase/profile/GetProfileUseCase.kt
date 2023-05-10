package ee.pw.edu.pl.domain.usecase.profile

import javax.inject.Inject
import kotlinx.coroutines.delay

class GetProfileUseCase @Inject constructor() {
    suspend operator fun invoke(): Profile {
        delay(5000L)
        return Profile(
            email = "example@gmail.com",
            name = "name",
            surname = "surname",
            imageUrl = "https://cdn.pixabay.com/photo/2017/04/01/21/06/portrait-2194457_960_720.jpg",
            shortDescription = "Jestem silnym i niezależnym programistą, który poszukuje miłości na dłużej. Jeśli chcesz się ze mną spotkać napisz do mnie :)",
        )
    }
}
