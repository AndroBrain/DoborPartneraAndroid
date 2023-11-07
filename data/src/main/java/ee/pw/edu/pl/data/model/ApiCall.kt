package ee.pw.edu.pl.data.model

import java.net.UnknownHostException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> apiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    call: suspend () -> T
) = withContext(dispatcher) {
    try {
        ApiResponse.Ok(value = call.invoke())
    } catch (httpException: HttpException) {
        ApiResponse.Error(ApiException(httpException.code(), httpException.response()))
    } catch (unknownHostException: UnknownHostException) {
        ApiResponse.NetworkError(unknownHostException)
    }
}

suspend fun <T> apiCallWithHeaders(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    call: suspend () -> Response<T>
) = withContext(dispatcher) {
    try {
        val response = call.invoke()
        when {
            response.isSuccessful -> ApiResponseWithHeaders.Ok(
                headers = response.headers(),
                body = response.body()!!
            )

            else -> ApiResponseWithHeaders.Error(ApiException(response.code(), response))
        }
    } catch (httpException: HttpException) {
        ApiResponseWithHeaders.Error(ApiException(httpException.code(), httpException.response()))
    } catch (unknownHostException: UnknownHostException) {
        ApiResponseWithHeaders.NetworkError(unknownHostException)
    }
}
