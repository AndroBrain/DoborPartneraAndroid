package ee.pw.edu.pl.domain.repository

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.usecase.account.Account
import ee.pw.edu.pl.domain.usecase.account.EditAccountForm

interface AccountRepository {
    suspend fun updateProfile(profileForm: EditAccountForm): UseCaseResult<Unit>
    suspend fun getProfile(): UseCaseResult<Account>
}
