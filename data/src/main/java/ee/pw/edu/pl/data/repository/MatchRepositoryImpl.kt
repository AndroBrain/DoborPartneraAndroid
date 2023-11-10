package ee.pw.edu.pl.data.repository

import ee.pw.edu.pl.data.datasource.match.MatchRemoteDataSource
import ee.pw.edu.pl.data.model.ApiResponseWithHeaders
import ee.pw.edu.pl.domain.core.result.ResultErrorType
import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.MatchRepository
import ee.pw.edu.pl.domain.usecase.match.MatchProfile
import java.util.*
import java.util.concurrent.TimeUnit

class MatchRepositoryImpl(
    private val matchRemoteDataSource: MatchRemoteDataSource,
) : MatchRepository {
    override suspend fun getMatches(): UseCaseResult<List<MatchProfile>> =
        when (val response = matchRemoteDataSource.getMatches()) {
            is ApiResponseWithHeaders.Error -> UseCaseResult.Error(ResultErrorType.NOT_FOUND)
            is ApiResponseWithHeaders.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponseWithHeaders.Ok -> {
                val data = response.body
                UseCaseResult.Ok(
                    data.map { match ->
                        MatchProfile(
                            id = match.userId,
                            name = match.name,
                            age = inYearsToNow(match.birthdate).toString(),
                            description = match.description,
                            avatar = match.avatar,
                            images = match.images,
                            interests = match.interests,
                        )
                    }
                )
            }
        }

    private fun inYearsToNow(startDate: Date): Long {
        val startCalendar = Calendar.getInstance()
        startCalendar.time = startDate
        val currentCalendar = Calendar.getInstance()
        val diffInMillis = currentCalendar.timeInMillis - startCalendar.timeInMillis
        return TimeUnit.MILLISECONDS.toDays(diffInMillis) / 365
    }
}
