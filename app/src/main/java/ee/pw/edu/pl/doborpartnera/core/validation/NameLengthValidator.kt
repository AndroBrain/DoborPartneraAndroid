package ee.pw.edu.pl.doborpartnera.core.validation

private const val DEFAULT_MIN_LENGTH = 3
private const val DEFAULT_MAX_LENGTH = 50

class NameLengthValidator(
    override val errorMessage: Int,
    private val min: Int = DEFAULT_MIN_LENGTH,
    private val max: Int = DEFAULT_MAX_LENGTH,
) :
    FieldValidator {
    override fun validate(field: Any?) =
        field is String && field.isNotEmpty() && field.length >= min && field.length <= max
}
