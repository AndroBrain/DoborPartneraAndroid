package ee.pw.edu.pl.domain.repository

import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getChat(): Flow<String>
}