package ee.pw.edu.pl.domain.repository

import ee.pw.edu.pl.domain.core.result.UseCaseResult
import ee.pw.edu.pl.domain.usecase.message.Message
import ee.pw.edu.pl.domain.usecase.message.profile.ProfileWithMessages
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getMessages(id: Int): Flow<List<Message>>
    fun getProfilesWithMessages(): Flow<List<ProfileWithMessages>>
    suspend fun updateProfilesWithMessages(): UseCaseResult<Unit>
    suspend fun loadMoreMessages(id: Int): UseCaseResult<Boolean>
}
