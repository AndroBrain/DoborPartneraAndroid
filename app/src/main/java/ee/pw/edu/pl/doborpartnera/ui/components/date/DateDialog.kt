package ee.pw.edu.pl.doborpartnera.ui.components.date

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ee.pw.edu.pl.doborpartnera.R
import java.util.Calendar

private const val MIN_YEAR = 1900
private const val MIN_REQUIRED_AGE = 18

@Composable
fun DateDialog(
    modifier: Modifier = Modifier,
    state: DatePickerState = rememberDatePickerState(
        yearRange = IntRange(
            MIN_YEAR,
            Calendar.getInstance().get(Calendar.YEAR) - MIN_REQUIRED_AGE,
        )
    ),
    onDismissRequest: () -> Unit,
    onConfirm: (Long) -> Unit,
) {
    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    state.selectedDateMillis?.let(onConfirm)
                    onDismissRequest()
                },
                enabled = state.selectedDateMillis != null,
            ) {
                Text(text = stringResource(id = R.string.set))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    ) {
        DatePicker(state = state)
    }
}
