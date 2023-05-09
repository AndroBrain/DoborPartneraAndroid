package ee.pw.edu.pl.doborpartnera.core.validation

import ee.pw.edu.pl.doborpartnera.R

class RepeatPasswordValidator(private val password: String) : FieldValidator {
    override val errorMessage: Int
        get() = R.string.validation_err_repeat_password

    override fun validate(field: Any?) =
        field is String && field == password
}
