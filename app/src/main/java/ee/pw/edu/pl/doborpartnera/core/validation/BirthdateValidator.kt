package ee.pw.edu.pl.doborpartnera.core.validation

import ee.pw.edu.pl.doborpartnera.R

object BirthdateValidator : FieldValidator {
    override val errorMessage: Int
        get() = R.string.validation_err_date_cannot_be_empty

    override fun validate(field: Any?) = field is Long? && field != null
}