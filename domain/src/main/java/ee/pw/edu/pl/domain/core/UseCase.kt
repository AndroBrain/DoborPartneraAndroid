package ee.pw.edu.pl.domain.core

interface UseCase<in Param, out Result> {
    suspend operator fun invoke(param: Param): Result
}
