package ee.pw.edu.pl.domain.usecase.match

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMatchProfileUseCase @Inject constructor(

) {
    operator fun invoke(id: Long): Flow<UseCaseResult<MatchProfile>> = flow {
        delay(2000L)
        emit(
            UseCaseResult.Ok(
                MatchProfile(
                    name = "Ala",
                    age = "20",
                    shortDescription = "Krótki opis",
                    profilePhotoUrl = "https://images.pexels.com/photos/619981/pexels-photo-619981.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    galleryImages = listOf(
                        "https://images.pexels.com/photos/620075/pexels-photo-620075.jpeg",
                        "https://images.pexels.com/photos/620074/pexels-photo-620074.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                        "https://images.pexels.com/photos/618701/pexels-photo-618701.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                    )
                ),
            )
        )
    }
}
