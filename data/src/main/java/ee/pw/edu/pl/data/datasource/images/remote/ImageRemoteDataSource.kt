package ee.pw.edu.pl.data.datasource.images.remote

import kotlinx.coroutines.flow.Flow

interface ImageRemoteDataSource {
    fun uploadImage(bytes: ByteArray, format: String): Flow<String?>
}
