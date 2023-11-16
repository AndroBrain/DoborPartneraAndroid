package ee.pw.edu.pl.data.datasource.profile.local

import ee.pw.edu.pl.data.model.profile.local.ProfileEntity

class RoomProfileLocalDataSource(
    private val profileDao: ProfileDao,
) : ProfileLocalDataSource {
    override suspend fun insert(profiles: List<ProfileEntity>) {
        profiles.forEach { profile ->
            profileDao.insert(profile)
        }
    }
}
