package ee.pw.edu.pl.doborpartnera.core.validation

import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.domain.usecase.profile.Gender

object GenderValidation : FieldValidator {
    override val errorMessage: Int
        get() = R.string.validation_err_gender_cannot_be_empty

    override fun validate(field: Any?) = field is Gender? && field != null
}