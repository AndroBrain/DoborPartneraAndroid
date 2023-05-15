package ee.pw.edu.pl.doborpartnera.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun RefreshBox(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    onClick: () -> Unit,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                modifier = Modifier.size(App.dimens.main_icon_size),
                imageVector = Icons.Default.Wifi,
                contentDescription = null,
            )
            Text(
                modifier = Modifier.padding(vertical = App.dimens.views_spacing_medium),
                text = stringResource(id = R.string.connection_lost),
            )
            LoadingButton(onClick = onClick, isLoading = isLoading) {
                Text(text = stringResource(id = R.string.refresh))
            }
        }
    }
}
