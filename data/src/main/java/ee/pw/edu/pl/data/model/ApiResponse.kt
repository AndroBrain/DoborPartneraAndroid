package ee.pw.edu.pl.data.model

import okhttp3.Headers
import retrofit2.Response

sealed class ApiResponse<out T> {
    data class Ok<out T>(
        val headers: Headers,
        val body: T,
    ) : ApiResponse<T>()

    data class Error(
        val exception: ApiException
    ) : ApiResponse<Nothing>() {
        override fun isNotFound() = exception.httpCode == 404
        override fun isUnauthorized() = exception.httpCode == 401
    }

    data class NetworkError(
        val throwable: Throwable
    ) : ApiResponse<Nothing>()

    open fun isNotFound() = false
    open fun isUnauthorized() = false
}

class ApiException(
    val httpCode: Int,
    val errorBody: Response<*>?
) : Exception()
