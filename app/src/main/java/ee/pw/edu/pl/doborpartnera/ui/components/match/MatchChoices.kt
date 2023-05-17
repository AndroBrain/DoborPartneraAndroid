package ee.pw.edu.pl.doborpartnera.ui.components.match

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun MatchChoices(
    modifier: Modifier = Modifier,
    onAccept: (() -> Unit)? = null,
    onDecline: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier,
    ) {
        onDecline?.let {
            FloatingActionButton(
                modifier = Modifier.align(Alignment.BottomStart),
                onClick = onDecline,
                containerColor = MaterialTheme.colorScheme.errorContainer,
            ) {
                Row(
                    modifier = Modifier
                        .padding(
                            start = App.dimens.fab_text_padding,
                            end = App.dimens.views_spacing_medium,
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = stringResource(id = R.string.match_decline))
                    Icon(
                        modifier = Modifier.padding(start = App.dimens.views_spacing_small),
                        imageVector = Icons.Default.Block,
                        contentDescription = null,
                    )
                }
            }
        }
        onAccept?.let {
            FloatingActionButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = onAccept,
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            ) {
                Row(
                    modifier = Modifier
                        .padding(
                            start = App.dimens.views_spacing_medium,
                            end = App.dimens.fab_text_padding,
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        modifier = Modifier.padding(end = App.dimens.views_spacing_small),
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                    )
                    Text(text = stringResource(id = R.string.match_accept))
                }
            }
        }
    }
}