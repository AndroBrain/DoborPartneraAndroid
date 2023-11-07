package ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.components.LoadingButton
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: EditProfileViewModel,
    navigateUp: () -> Unit,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    state.value.errorMsg?.let { errorMsg ->
        val context = LocalContext.current
        LaunchedEffect(errorMsg) {
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
            viewModel.clearErrorMsg()
        }
    }
    LaunchedEffect(state.value.isSaved) {
        if (state.value.isSaved) {
            navigateUp()
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.profile_edit))
                },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { insets ->
        val textFieldModifier =
            Modifier
                .fillMaxWidth()
                .padding(top = App.dimens.views_spacing_small)
        LazyColumn(
            modifier = Modifier
                .padding(insets)
                .padding(horizontal = App.dimens.screen_spacing_medium)
                .fillMaxWidth()
        ) {
            item {
                OutlinedTextField(
                    modifier = textFieldModifier,
                    value = state.value.description,
                    onValueChange = viewModel::updateDescription,
                    label = { Text(text = stringResource(id = R.string.short_description_label)) },
                    supportingText = {
                        val descriptionError = state.value.descriptionError
                        if (descriptionError == null) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "${state.value.description.length} / $DESCRIPTION_MAX_LENGTH",
                                textAlign = TextAlign.End,
                            )
                        } else {
                            Text(text = stringResource(id = descriptionError))
                        }
                    },
                    isError = state.value.descriptionError != null,
                    minLines = 4,
                )
            }
            item {
                LoadingButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = App.dimens.views_spacing_small),
                    onClick = viewModel::save,
                    isLoading = state.value.isLoading,
                ) {
                    Text(text = stringResource(id = R.string.save))
                }
            }
        }
    }
}
