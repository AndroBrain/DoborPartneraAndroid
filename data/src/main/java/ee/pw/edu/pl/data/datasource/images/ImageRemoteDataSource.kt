package ee.pw.edu.pl.data.datasource.images

import kotlinx.coroutines.flow.Flow

interface ImageRemoteDataSource {
    fun uploadImage(bytes: ByteArray): Flow<String?>
}
