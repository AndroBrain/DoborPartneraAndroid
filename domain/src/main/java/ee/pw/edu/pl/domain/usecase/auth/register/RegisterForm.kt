package ee.pw.edu.pl.domain.usecase.auth.register

data class RegisterForm(
    val email: String,
    val name: String,
    val surname: String,
    val password: String,
)
