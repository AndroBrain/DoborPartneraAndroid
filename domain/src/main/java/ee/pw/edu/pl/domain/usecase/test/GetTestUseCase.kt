package ee.pw.edu.pl.domain.usecase.test

import ee.pw.edu.pl.domain.core.UseCase
import javax.inject.Inject

class GetTestUseCase @Inject constructor(
    private val testRepository: TestRepository,
) : UseCase<Unit, Test> {
    override suspend fun invoke(param: Unit) = testRepository.getTest()
}
