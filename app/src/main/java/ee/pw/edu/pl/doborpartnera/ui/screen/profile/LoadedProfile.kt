package ee.pw.edu.pl.doborpartnera.ui.screen.profile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun LoadedProfile(modifier: Modifier = Modifier, state: ProfileState) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = App.dimens.screen_spacing_large),
    ) {
        item {
            ProfileImage(state.imageUrl)
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
                text = state.shortDescription,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
        }
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = App.dimens.views_spacing_medium)
                    .padding(bottom = App.dimens.views_spacing_small),
                text = stringResource(id = R.string.profile_your_images),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
            )
        }
        item {
            Divider()
        }
        item {
//            TODO show person images here
        }
    }
}
