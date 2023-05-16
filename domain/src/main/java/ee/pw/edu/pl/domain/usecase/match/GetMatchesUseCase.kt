package ee.pw.edu.pl.domain.usecase.match

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMatchesUseCase @Inject constructor(

) {
    operator fun invoke(): Flow<UseCaseResult<List<MatchProfile>>> = flow {
        delay(2000L)
        emit(
            UseCaseResult.Ok(
                listOf(
                    MatchProfile(
                        name = "Ala",
                        age = "20",
                        shortDescription = "Krótki opis mam",
                        profilePhotoUrl = "https://images.pexels.com/photos/619981/pexels-photo-619981.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                        galleryImages = listOf(
                            "https://images.pexels.com/photos/620075/pexels-photo-620075.jpeg",
                            "https://images.pexels.com/photos/620074/pexels-photo-620074.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                            "https://images.pexels.com/photos/618701/pexels-photo-618701.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                        )
                    ),
                    MatchProfile(
                        name = "Maja",
                        age = "30",
                        shortDescription = "Krótki opis mam",
                        profilePhotoUrl = "https://images.pexels.com/photos/9223149/pexels-photo-9223149.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                        galleryImages = listOf(
                            "https://images.pexels.com/photos/9223151/pexels-photo-9223151.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                            "https://images.pexels.com/photos/9223153/pexels-photo-9223153.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                            "https://images.pexels.com/photos/9223148/pexels-photo-9223148.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                            "https://images.pexels.com/photos/9223155/pexels-photo-9223155.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                            "https://images.pexels.com/photos/10591370/pexels-photo-10591370.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                        )
                    ),
                )
            )
        )
    }
}
