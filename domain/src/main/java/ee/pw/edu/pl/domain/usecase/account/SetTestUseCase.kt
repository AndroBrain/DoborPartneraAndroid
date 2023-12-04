package ee.pw.edu.pl.domain.usecase.account

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.AccountRepository

class SetTestUseCase(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(form: SetTestForm): UseCaseResult<Unit> =
        accountRepository.setTest(form)
}
