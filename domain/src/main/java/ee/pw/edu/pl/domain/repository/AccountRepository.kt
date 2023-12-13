package ee.pw.edu.pl.domain.repository

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.usecase.account.Account
import ee.pw.edu.pl.domain.usecase.account.EditAccountForm
import ee.pw.edu.pl.domain.usecase.test.SetTestForm

interface AccountRepository {
    suspend fun update(profileForm: EditAccountForm): UseCaseResult<Unit>
    suspend fun get(): UseCaseResult<Account>
    suspend fun setTest(test: SetTestForm): UseCaseResult<Unit>
}
