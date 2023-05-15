package ee.pw.edu.pl.domain.core.result

sealed class UseCaseResult<out T> {
    data class Ok<out T>(val value: T) : UseCaseResult<T>()

    data class Error(val type: ResultErrorType = ResultErrorType.UNKNOWN) : UseCaseResult<Nothing>()
}

inline fun <R, T> UseCaseResult<T>.fold(
    onOk: (value: UseCaseResult.Ok<T>) -> R,
    onError: (error: UseCaseResult.Error) -> R
): R {
    return when (this) {
        is UseCaseResult.Ok -> onOk(this)
        is UseCaseResult.Error -> onError(this)
    }
}
