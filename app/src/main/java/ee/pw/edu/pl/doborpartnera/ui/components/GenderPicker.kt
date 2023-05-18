package ee.pw.edu.pl.doborpartnera.ui.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.domain.usecase.profile.Gender

@Composable
fun GenderPicker(
    modifier: Modifier = Modifier,
    selectedOption: Gender?,
    onSelectedOptionChanged: (Gender) -> Unit,
) {
    val isExpanded = remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = isExpanded.value,
        onExpandedChange = { isExpanded.value = it },
    ) {
        OutlinedTextField(
            modifier = modifier.menuAnchor(),
            readOnly = true,
            value = stringResource(id = selectedOption.getLabel()),
            onValueChange = {},
            label = { Text(text = stringResource(id = R.string.gender_label)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded.value)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false },
        ) {
            Gender.values().forEach { gender ->
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = gender.getLabel())) },
                    onClick = {
                        onSelectedOptionChanged(gender)
                        isExpanded.value = false
                    },
                )
            }
        }
    }
}

private fun Gender?.getLabel() = when (this) {
    Gender.MALE -> R.string.gender_male
    Gender.FEMALE -> R.string.gender_female
    else -> R.string.empty
}
