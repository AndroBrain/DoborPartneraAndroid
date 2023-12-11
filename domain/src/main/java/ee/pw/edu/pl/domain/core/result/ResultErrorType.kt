package ee.pw.edu.pl.domain.core.result

enum class ResultErrorType {
    //    Login
    INVALID_CREDENTIALS,

    //    Register
    EMAIL_TAKEN,

    //    Common
    NETWORK,
    UNKNOWN,
    UNAUTHORIZED,
    FORBIDDEN,
    NOT_FOUND,
    TOO_MANY_REQUESTS,
    SERVER,

    //    Image
    PROFILE_FILE_TOO_LARGE,
    PROFILE_IMAGE_TOO_LARGE;
}
