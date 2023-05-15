package ee.pw.edu.pl.domain.core.result

data class LoginResponse(
    val token: String,
    val isTestFilled: Boolean,
)
