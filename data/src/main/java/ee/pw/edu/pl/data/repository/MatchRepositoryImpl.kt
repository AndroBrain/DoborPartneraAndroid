package ee.pw.edu.pl.data.repository

import ee.pw.edu.pl.data.datasource.match.remote.MatchRemoteDataSource
import ee.pw.edu.pl.data.model.ApiResponse
import ee.pw.edu.pl.data.model.match.remote.DeclineMatchRequest
import ee.pw.edu.pl.data.model.match.remote.MatchRequest
import ee.pw.edu.pl.data.model.match.remote.MatchResponse
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
            is ApiResponse.Error -> UseCaseResult.Error(ResultErrorType.NOT_FOUND)
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> {
                val data = response.body
                UseCaseResult.Ok(
                    data.map { match -> match.toModel() }
                )
            }
        }

    override suspend fun decline(id: Int): UseCaseResult<Unit> =
        when (matchRemoteDataSource.decline(DeclineMatchRequest(id = id))) {
            is ApiResponse.Error -> UseCaseResult.Error(ResultErrorType.UNKNOWN)
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> UseCaseResult.Ok(Unit)
        }

    override suspend fun getMatch(id: Int): UseCaseResult<MatchProfile> =
        when (val response = matchRemoteDataSource.getMatch(MatchRequest(id = id))) {
            is ApiResponse.Error -> UseCaseResult.Error(ResultErrorType.UNKNOWN)
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> UseCaseResult.Ok(response.body.toModel())
        }

    private fun MatchResponse.toModel() = MatchProfile(
        id = userId,
        name = name,
        age = inYearsToNow(birthdate).toString(),
        description = description,
        avatar = avatar,
        images = images,
        interests = interests,
    )

    private fun inYearsToNow(startDate: Date): Long {
        val startCalendar = Calendar.getInstance()
        startCalendar.time = startDate
        val currentCalendar = Calendar.getInstance()
        val diffInMillis = currentCalendar.timeInMillis - startCalendar.timeInMillis
        return TimeUnit.MILLISECONDS.toDays(diffInMillis) / 365
    }
}
