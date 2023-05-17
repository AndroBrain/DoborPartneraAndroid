package ee.pw.edu.pl.doborpartnera.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ee.pw.edu.pl.doborpartnera.R

@Composable
fun TextDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    title: String,
    confirm: String,
    dismiss: String = stringResource(id = R.string.cancel),
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        title = {
            Text(text = title)
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = confirm)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = dismiss)
            }
        }
    )
}
