package ee.pw.edu.pl.domain.usecase.profile

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.ProfileRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EditProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    operator fun invoke(form: EditProfileForm): Flow<UseCaseResult<Unit>> = flow {
        emit(profileRepository.updateProfile(form))
    }
}
