package ee.pw.edu.pl.domain.repository

interface ProfileRepository {
    suspend fun remove(id: Int)
}
