package ee.pw.edu.pl.doborpartnera.core.validation

import ee.pw.edu.pl.doborpartnera.R

object PasswordValidator : FieldValidator {
    override val errorMessage: Int
        get() = R.string.validation_err_password

    override fun validate(field: Any?) =
        field is String && field.length >= 8 && field.any { it.isUpperCase() } && field.any { it.isLowerCase() } && field.any { it.isDigit() }
}