package ee.pw.edu.pl.data.injection

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ee.pw.edu.pl.data.R
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageModule {

    @Singleton
    @Provides
    fun provideFirebaseStorage(@ApplicationContext context: Context): FirebaseStorage {
        FirebaseApp.initializeApp(context)
        return FirebaseStorage.getInstance(context.getString(R.string.firebase_cloud_storage_url))
    }

}
