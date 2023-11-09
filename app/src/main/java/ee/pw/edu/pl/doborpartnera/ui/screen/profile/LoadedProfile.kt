package ee.pw.edu.pl.doborpartnera.ui.screen.profile

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.components.image.PhotoImage
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LoadedProfile(
    modifier: Modifier = Modifier,
    state: ProfileState,
    onEditProfileClicked: () -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = App.dimens.screen_spacing_large),
    ) {
        item {
            ProfileImage(url = state.avatar, enabled = false)
        }
        item {
            Text(
                modifier = Modifier
                    .padding(top = App.dimens.views_spacing_medium)
                    .fillMaxWidth(),
                text = "${state.name} ${state.surname}",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
            )
        }
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = App.dimens.views_spacing_medium),
                text = state.description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
        }
        item {
            OutlinedButton(
                modifier = Modifier.padding(top = App.dimens.views_spacing_small),
                onClick = onEditProfileClicked,
            ) {
                Text(text = stringResource(id = R.string.profile_edit))
            }
        }
        item {
            Divider()
        }
        item {
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
            ) {
                state.images.forEach { url ->
                    PhotoImage(url = url)
                }
            }
        }
    }
}
