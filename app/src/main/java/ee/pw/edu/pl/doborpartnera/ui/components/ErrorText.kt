package ee.pw.edu.pl.doborpartnera.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun ErrorText(
    modifier: Modifier = Modifier,
    text: String,
    paddingValues: PaddingValues = PaddingValues(start = App.dimens.views_spacing_medium),
) {
    Text(
        modifier = modifier.padding(paddingValues),
        text = text,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall,
    )
}