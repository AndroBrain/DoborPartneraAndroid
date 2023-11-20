package ee.pw.edu.pl.data.injection

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ee.pw.edu.pl.data.BuildConfig
import ee.pw.edu.pl.data.R
import ee.pw.edu.pl.data.core.remote.ApiService
import ee.pw.edu.pl.data.core.remote.interceptor.AuthInterceptor
import ee.pw.edu.pl.data.datasource.auth.local.AuthLocalDataSource
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        isLenient = !BuildConfig.DEBUG
        ignoreUnknownKeys = !BuildConfig.DEBUG
        coerceInputValues = !BuildConfig.DEBUG
        prettyPrint = BuildConfig.DEBUG
    }

    @Provides
    fun provideAuthInterceptor(authLocalDataSource: AuthLocalDataSource) =
        AuthInterceptor(authLocalDataSource)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        authInterceptor: AuthInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(
                context.resources.getInteger(R.integer.api_connect_timeout_seconds).toLong(),
                TimeUnit.SECONDS
            )
            .readTimeout(
                context.resources.getInteger(R.integer.api_read_timeout_seconds).toLong(),
                TimeUnit.SECONDS
            )
            .addInterceptor(authInterceptor)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                }
            }
            .build()

    @Provides
    @Singleton
    fun provideApiService(
        @ApplicationContext context: Context,
        json: Json,
        okHttpClient: OkHttpClient
    ): ApiService = Retrofit.Builder()
        .baseUrl(context.getString(R.string.api_url))
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(ApiService::class.java)
}
