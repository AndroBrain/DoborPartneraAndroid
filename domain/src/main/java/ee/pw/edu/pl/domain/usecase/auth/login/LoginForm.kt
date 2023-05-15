package ee.pw.edu.pl.domain.usecase.auth.login

data class LoginForm(
    val email: String,
    val password: String,
)
