package ee.pw.edu.pl.doborpartnera.core.validation

import androidx.annotation.StringRes

class CollectionSizeValidator(
    @StringRes override val errorMessage: Int,
    private val min: Int = 0,
    private val max: Int = Int.MAX_VALUE,
) : FieldValidator {
    override fun validate(field: Any?) =
        field is Collection<*> && field.size >= min && field.size <= max
}
