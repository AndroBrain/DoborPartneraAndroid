package ee.pw.edu.pl.data.model.auth

import ee.pw.edu.pl.data.model.auth.remote.LoginRequest
import ee.pw.edu.pl.data.model.auth.remote.RegisterRequest
import ee.pw.edu.pl.domain.usecase.auth.login.LoginForm
import ee.pw.edu.pl.domain.usecase.auth.register.RegisterForm
import java.util.*

fun RegisterForm.toRequest() = RegisterRequest(
    email = email,
    name = name,
    surname = surname,
    gender = gender.name,
    birthdate = Date(birthdate),
    password = password,
)

fun LoginForm.toRequest() = LoginRequest(email = email, password = password)
