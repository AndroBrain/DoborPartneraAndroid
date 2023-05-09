package ee.pw.edu.pl.doborpartnera.core.validation

import android.util.Patterns
import ee.pw.edu.pl.doborpartnera.R

object EmailValidator : FieldValidator {
    override val errorMessage: Int
        get() = R.string.validation_err_email

    override fun validate(field: Any?): Boolean =
        field is String? && Patterns.EMAIL_ADDRESS.matcher(field ?: "").find()
}
