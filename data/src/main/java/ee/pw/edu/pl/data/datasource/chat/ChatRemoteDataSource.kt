package ee.pw.edu.pl.data.datasource.chat

import kotlinx.coroutines.flow.Flow

interface ChatRemoteDataSource {
    fun connectToChat(): Flow<String>
}
