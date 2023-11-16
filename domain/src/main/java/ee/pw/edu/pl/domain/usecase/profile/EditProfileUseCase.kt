package ee.pw.edu.pl.domain.usecase.profile

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.AccountRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EditProfileUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    operator fun invoke(form: EditProfileForm): Flow<UseCaseResult<Unit>> = flow {
        emit(accountRepository.updateProfile(form))
    }
}
