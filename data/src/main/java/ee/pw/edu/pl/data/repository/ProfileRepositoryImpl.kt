package ee.pw.edu.pl.data.repository

import ee.pw.edu.pl.data.datasource.profile.local.ProfileLocalDataSource
import ee.pw.edu.pl.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val profileLocalDataSource: ProfileLocalDataSource,
) : ProfileRepository {
    override suspend fun remove(id: Int) {
        profileLocalDataSource.remove(id)
    }
}
