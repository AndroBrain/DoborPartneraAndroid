package ee.pw.edu.pl.domain.repository

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.usecase.profile.EditProfileForm

interface ProfileRepository {
    suspend fun updateProfile(profileForm: EditProfileForm): UseCaseResult<Unit>
}
