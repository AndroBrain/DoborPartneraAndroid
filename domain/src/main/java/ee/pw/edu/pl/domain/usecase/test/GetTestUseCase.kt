package ee.pw.edu.pl.domain.usecase.test

import javax.inject.Inject

class GetTestUseCase @Inject constructor(
    private val testRepository: TestRepository,
) {
    suspend operator fun invoke() = testRepository.getTest()
}
