package ee.pw.edu.pl.domain.usecase.match

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMatchesUseCase @Inject constructor(

) {
    operator fun invoke(): Flow<UseCaseResult<List<MatchProfile>>> = flow {
        emit(
            UseCaseResult.Ok(
                listOf(
                    MatchProfile(
                        name = "Ala",
                        age = "Kowalska",
                        shortDescription = "Krótki opis mam",
                        profilePhotoUrl = "https://cdn.pixabay.com/photo/2018/01/13/19/39/fashion-3080644_1280.jpg",
                        galleryImages = listOf(
                            "https://cdn.pixabay.com/photo/2020/12/13/16/37/woman-5828786_960_720.jpg",
                            "https://cdn.pixabay.com/photo/2014/12/06/19/46/girl-559307_960_720.jpg",
                            "https://cdn.pixabay.com/photo/2015/01/12/10/44/woman-597173_960_720.jpg",
                        )
                    )
                )
            )
        )
    }
}
