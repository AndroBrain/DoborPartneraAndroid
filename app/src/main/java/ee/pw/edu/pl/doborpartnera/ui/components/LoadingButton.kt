package ee.pw.edu.pl.doborpartnera.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun LoadingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isLoading: Boolean,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = !isLoading,
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.size(App.dimens.loading_button_indicator_size))
        } else {
            content()
        }
    }
}