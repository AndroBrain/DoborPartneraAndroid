package ee.pw.edu.pl.data.datasource.profile.local

import ee.pw.edu.pl.data.model.profile.local.ProfileEntity

interface ProfileLocalDataSource {
    suspend fun insert(profiles: List<ProfileEntity>)
    suspend fun remove(id: Int)
    suspend fun removeAll()
}
