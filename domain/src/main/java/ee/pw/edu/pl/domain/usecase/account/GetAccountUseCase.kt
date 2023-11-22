package ee.pw.edu.pl.domain.usecase.account

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.AccountRepository

class GetAccountUseCase(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(): UseCaseResult<Account> = accountRepository.get()
}
