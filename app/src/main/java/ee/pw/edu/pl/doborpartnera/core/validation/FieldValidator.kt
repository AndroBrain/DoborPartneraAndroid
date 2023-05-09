package ee.pw.edu.pl.doborpartnera.core.validation

import androidx.annotation.StringRes

interface FieldValidator {
    @get:StringRes
    val errorMessage: Int

    fun validate(field: Any?): Boolean
}
