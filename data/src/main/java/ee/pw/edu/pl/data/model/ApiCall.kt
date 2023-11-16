package ee.pw.edu.pl.data.model

import java.net.UnknownHostException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> apiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    call: suspend () -> Response<T>
) = withContext(dispatcher) {
    try {
        val response = call.invoke()
        when {
            response.isSuccessful -> ApiResponse.Ok(
                headers = response.headers(),
                body = response.body()!!
            )

            else -> ApiResponse.Error(ApiException(response.code(), response))
        }
    } catch (httpException: HttpException) {
        ApiResponse.Error(ApiException(httpException.code(), httpException.response()))
    } catch (unknownHostException: UnknownHostException) {
        ApiResponse.NetworkError(unknownHostException)
    }
}
