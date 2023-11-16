package ee.pw.edu.pl.data.repository

import ee.pw.edu.pl.data.datasource.profile.local.ProfileLocalDataSource
import ee.pw.edu.pl.data.model.profile.local.ProfileEntity
import ee.pw.edu.pl.domain.repository.ProfileRepository
import ee.pw.edu.pl.domain.usecase.profile.Profile

class ProfileRepositoryImpl(
    private val profileLocalDataSource: ProfileLocalDataSource,
) : ProfileRepository {
    override suspend fun remove(id: Int) {
        profileLocalDataSource.remove(id)
    }

    override suspend fun removeAll() {
        profileLocalDataSource.removeAll()
    }

    override suspend fun insert(profiles: List<Profile>) {
        profileLocalDataSource.insert(
            profiles.map { profile ->
                ProfileEntity(
                    id = profile.id, name = profile.name, avatar = profile.avatar,
                )
            }
        )
    }
}
