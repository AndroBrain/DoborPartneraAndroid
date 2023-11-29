package ee.pw.edu.pl.domain.usecase.account

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val MAX_IMAGE_BYTES = 5e+6
private const val MAX_PROFILE_BYTES = 2e+6

class EditAccountUseCase(private val accountRepository: AccountRepository) {
    operator fun invoke(form: EditAccountForm): Flow<UseCaseResult<Unit>> = flow {
        if (form.images.any { it.bytes != null && it.bytes.size > MAX_IMAGE_BYTES }) {
            emit(UseCaseResult.Error())
        } else if (form.profileAvatar.bytes != null && form.profileAvatar.bytes.size > MAX_PROFILE_BYTES) {
            emit(UseCaseResult.Error())
        } else {
            emit(accountRepository.update(form))
        }
    }
}
