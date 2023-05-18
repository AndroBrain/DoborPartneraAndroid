package ee.pw.edu.pl.doborpartnera.ui.components.date

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import java.text.DateFormat

@Composable
fun DateTextField(
    modifier: Modifier = Modifier,
    date: Long?,
    onDateTimestampChanged: (Long) -> Unit,
    label: String,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
) {
    val openDateDialog = remember { mutableStateOf(false) }
    if (openDateDialog.value) {
        DateDialog(
            onDismissRequest = { openDateDialog.value = false },
            onConfirm = onDateTimestampChanged,
        )
    }
    val dateText = if (date == null) {
        ""
    } else {
        val dateTimeFormatter = DateFormat.getDateInstance()
        dateTimeFormatter.format(date)
    }
    val dateInteractionSource = remember { MutableInteractionSource() }
    LaunchedEffect(dateInteractionSource) {
        dateInteractionSource.interactions.collect {
            if (it is PressInteraction.Press) {
                openDateDialog.value = true
            }
        }
    }
    OutlinedTextField(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures { openDateDialog.value = true }
            },
        value = dateText,
        onValueChange = {},
        readOnly = true,
        isError = isError,
        trailingIcon = {
            Icon(imageVector = Icons.Default.CalendarToday, contentDescription = null)
        },
        interactionSource = dateInteractionSource,
        label = { Text(text = label) },
        supportingText = supportingText,
    )
}