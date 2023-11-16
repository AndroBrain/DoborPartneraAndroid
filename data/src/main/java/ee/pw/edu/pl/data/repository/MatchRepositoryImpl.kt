package ee.pw.edu.pl.data.repository

import ee.pw.edu.pl.data.datasource.match.remote.MatchRemoteDataSource
import ee.pw.edu.pl.data.model.ApiResponse
import ee.pw.edu.pl.data.model.match.remote.DeclineMatchRequest
import ee.pw.edu.pl.data.model.match.remote.MatchRequest
import ee.pw.edu.pl.data.model.match.toModel
import ee.pw.edu.pl.data.model.match.toModels
import ee.pw.edu.pl.domain.core.result.ResultErrorType
import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.repository.MatchRepository
import ee.pw.edu.pl.domain.usecase.match.MatchProfile

class MatchRepositoryImpl(
    private val matchRemoteDataSource: MatchRemoteDataSource,
) : MatchRepository {
    override suspend fun getMatches(): UseCaseResult<List<MatchProfile>> =
        when (val response = matchRemoteDataSource.getMatches()) {
            is ApiResponse.Error -> UseCaseResult.Error(ResultErrorType.NOT_FOUND)
            is ApiResponse.NetworkError -> UseCaseResult.Error(ResultErrorType.NETWORK)
            is ApiResponse.Ok -> UseCaseResult.Ok(response.body.toModels())
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
}
