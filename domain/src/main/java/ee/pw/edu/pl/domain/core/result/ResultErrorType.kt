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

    //    Image
    PROFILE_FILE_TOO_LARGE,
    PROFILE_IMAGE_TOO_LARGE;
}
