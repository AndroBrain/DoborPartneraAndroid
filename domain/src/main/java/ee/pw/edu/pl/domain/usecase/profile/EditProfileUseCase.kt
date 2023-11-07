package ee.pw.edu.pl.domain.usecase.profile

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EditProfileUseCase @Inject constructor(

) {
    operator fun invoke(profile: EditProfileForm): Flow<UseCaseResult<Unit>> = flow {
        emit(UseCaseResult.Ok(Unit))
    }
}
