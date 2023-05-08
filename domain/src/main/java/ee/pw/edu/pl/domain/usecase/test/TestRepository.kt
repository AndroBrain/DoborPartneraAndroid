package ee.pw.edu.pl.domain.usecase.test

interface TestRepository {
    suspend fun getTest(): Test
}
