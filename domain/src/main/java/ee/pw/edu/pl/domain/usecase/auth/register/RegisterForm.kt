package ee.pw.edu.pl.domain.usecase.auth.register

import ee.pw.edu.pl.domain.usecase.account.Gender

data class RegisterForm(
    val email: String,
    val name: String,
    val surname: String,
    val password: String,
    val birthdate: Long,
    val gender: Gender,
)
