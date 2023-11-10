package ee.pw.edu.pl.doborpartnera.core.validation

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.R

class FieldNotNullValidator(
    @StringRes override val errorMessage: Int = R.string.validation_err_field_cannot_be_empty
) : FieldValidator {
    override fun validate(field: Any?) = field != null
}
