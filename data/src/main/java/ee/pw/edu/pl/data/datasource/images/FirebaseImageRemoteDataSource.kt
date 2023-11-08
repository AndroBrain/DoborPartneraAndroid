package ee.pw.edu.pl.data.datasource.images

import com.google.firebase.storage.FirebaseStorage
import java.util.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseImageRemoteDataSource(firebaseStorage: FirebaseStorage) : ImageRemoteDataSource {
    private val imageRef = firebaseStorage.reference.child("images")
    override fun uploadImage(bytes: ByteArray): Flow<String?> = callbackFlow {
//        TODO return url to image
        val ref = imageRef.child("${UUID.randomUUID()}.jpg")
        ref.putBytes(bytes).continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            ref.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                trySend(task.result.toString())
            } else {
                trySend(null)
            }
        }
        awaitClose()
    }
}
