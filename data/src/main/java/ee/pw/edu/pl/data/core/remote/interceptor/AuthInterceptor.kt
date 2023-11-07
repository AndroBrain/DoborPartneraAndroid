package ee.pw.edu.pl.data.core.remote.interceptor

import ee.pw.edu.pl.data.core.remote.withAccessToken
import ee.pw.edu.pl.data.datasource.auth.AuthLocalDataSource
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val authLocalDataSource: AuthLocalDataSource) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = authLocalDataSource.getToken().value
        return if (token.isNullOrEmpty()) {
            chain.proceed(chain.request())
        } else {
            chain.proceed(chain.request().withAccessToken(token))
        }
    }
}
