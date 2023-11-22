package ee.pw.edu.pl.domain.usecase.account

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EditAccountUseCase(private val accountRepository: AccountRepository) {
    operator fun invoke(form: EditAccountForm): Flow<UseCaseResult<Unit>> = flow {
        emit(accountRepository.update(form))
    }
}
