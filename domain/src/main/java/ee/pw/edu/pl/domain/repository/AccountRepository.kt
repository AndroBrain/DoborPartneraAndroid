package ee.pw.edu.pl.domain.repository

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.usecase.profile.EditProfileForm
import ee.pw.edu.pl.domain.usecase.profile.Profile

interface AccountRepository {
    suspend fun updateProfile(profileForm: EditProfileForm): UseCaseResult<Unit>
    suspend fun getProfile(): UseCaseResult<Profile>
}
