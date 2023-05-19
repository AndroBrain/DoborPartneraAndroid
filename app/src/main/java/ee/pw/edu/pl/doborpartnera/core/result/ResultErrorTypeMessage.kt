package ee.pw.edu.pl.doborpartnera.core.result

import androidx.annotation.StringRes
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.domain.core.result.ResultErrorType

@StringRes
fun ResultErrorType.getMessage() = when (this) {
    ResultErrorType.INVALID_CREDENTIALS -> R.string.result_err_invalid_data
    ResultErrorType.EMAIL_TAKEN -> R.string.result_err_email_taken
    ResultErrorType.NETWORK -> R.string.result_err_network
    ResultErrorType.UNKNOWN -> R.string.result_err_unknown
}
