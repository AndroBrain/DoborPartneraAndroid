package ee.pw.edu.pl.doborpartnera.core.validation

import ee.pw.edu.pl.doborpartnera.R

object FieldNotNullValidator : FieldValidator {
    override val errorMessage: Int
        get() = R.string.validation_err_field_cannot_be_empty

    override fun validate(field: Any?) = field != null
}
