package ee.pw.edu.pl.domain.core.result

enum class ResultErrorType {
    //    Login
    INVALID_CREDENTIALS,

    //    Register
    EMAIL_TAKEN,

    //    Common
    NETWORK,
    UNKNOWN,
    NOT_FOUND,
}
