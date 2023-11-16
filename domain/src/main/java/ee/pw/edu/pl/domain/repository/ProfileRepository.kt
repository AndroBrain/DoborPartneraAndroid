package ee.pw.edu.pl.domain.repository

import ee.pw.edu.pl.domain.usecase.profile.Profile

interface ProfileRepository {
    suspend fun remove(id: Int)
    suspend fun removeAll()
    suspend fun insert(profiles: List<Profile>)
}
