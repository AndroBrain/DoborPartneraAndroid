package ee.pw.edu.pl.doborpartnera.core.validation

import androidx.annotation.StringRes

object Validator {
    @JvmName("validateVararg")
    @StringRes
    fun validate(field: Any?, vararg validators: FieldValidator): Int? {
        return validate(field, validators)
    }

    @JvmName("validateArray")
    @StringRes
    fun validate(field: Any?, validators: Array<out FieldValidator>): Int? {
        validators.forEach { validator ->
            val validationStatus = validator.validate(field)
            if (!validationStatus) {
                return validator.errorMessage
            }
        }
        return null
    }
}
