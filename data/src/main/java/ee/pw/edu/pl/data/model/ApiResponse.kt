package ee.pw.edu.pl.data.model

import okhttp3.Headers
import retrofit2.Response

sealed class ApiResponse<out T> {
    data class Ok<out T>(
        val headers: Headers,
        val body: T,
    ) : ApiResponse<T>()

    data class Error(
        val exception: ApiException,
    ) : ApiResponse<Nothing>() {
        override fun isUnauthorized() = exception.httpCode == 401
        override fun isForbidden() = exception.httpCode == 403
        override fun isNotFound() = exception.httpCode == 404
        override fun isTooManyRequests() = exception.httpCode == 429
        override fun isServerError() = exception.httpCode >= 500
    }

    data class NetworkError(
        val throwable: Throwable,
    ) : ApiResponse<Nothing>()

    open fun isNotFound() = false
    open fun isUnauthorized() = false
    open fun isTooManyRequests() = false
    open fun isForbidden() = false
    open fun isServerError() = false
}

class ApiException(
    val httpCode: Int,
    val errorBody: Response<*>?,
) : Exception()
