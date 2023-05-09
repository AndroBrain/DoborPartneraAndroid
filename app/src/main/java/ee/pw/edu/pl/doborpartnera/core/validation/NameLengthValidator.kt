package ee.pw.edu.pl.doborpartnera.core.validation

class NameLengthValidator(override val errorMessage: Int) : FieldValidator {
    override fun validate(field: Any?) =
        field is String && field.isNotEmpty() && field.length >= 3 && field.length <= 50
}
