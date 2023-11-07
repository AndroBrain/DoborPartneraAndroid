package ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit

import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dokar.chiptextfield.Chip
import com.dokar.chiptextfield.ChipTextFieldState
import com.dokar.chiptextfield.m3.ChipTextFieldDefaults
import com.dokar.chiptextfield.m3.OutlinedChipTextField
import com.dokar.chiptextfield.rememberChipTextFieldState
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.components.LoadingButton
import ee.pw.edu.pl.doborpartnera.ui.screen.profile.ProfileImage
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
    LaunchedEffect(key1 = state.value, block = { Log.d("STATE", state.value.toString()) })
    val interestsState =
        rememberChipTextFieldState(chips = state.value.interests.map { Chip(it) })
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
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Spacer(modifier = Modifier.size(App.dimens.views_spacing_small))
            }
            item {
                ProfileImage(
                    url = state.value.profileImageUrl,
                    onImageChanged = viewModel::updateProfileImage,
                )
            }
            item {
                Spacer(modifier = Modifier.size(App.dimens.views_spacing_small))
            }
            item {
                Text(text = stringResource(id = R.string.profile_edit_avatar))
            }
            item {
                Spacer(modifier = Modifier.size(App.dimens.views_spacing_small))
            }
            item {
                DescriptionTextField(
                    modifier = textFieldModifier,
                    description = state.value.description,
                    descriptionError = state.value.descriptionError,
                    updateDescription = viewModel::updateDescription,
                )
            }
            item {
                Spacer(modifier = Modifier.size(App.dimens.views_spacing_small))
            }
            item {
                InterestsTextField(textFieldModifier, interestsState)
            }
            item {
                LoadingButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = App.dimens.views_spacing_small),
                    onClick = { viewModel.save(interests = interestsState.chips.map { it.text }) },
                    isLoading = state.value.isLoading,
                ) {
                    Text(text = stringResource(id = R.string.save))
                }
            }
        }
    }
}

@Composable
private fun InterestsTextField(
    modifier: Modifier,
    state: ChipTextFieldState<Chip>,
) {
    OutlinedChipTextField(
        modifier = modifier,
        state = state,
        onSubmit = ::Chip,
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
        label = { Text(text = stringResource(id = R.string.profile_interests)) },
        chipStyle = ChipTextFieldDefaults.chipStyle(
            focusedBackgroundColor = MaterialTheme.colorScheme.primary,
            unfocusedBackgroundColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
        )
    )
}

@Composable
private fun DescriptionTextField(
    modifier: Modifier,
    description: String,
    @StringRes descriptionError: Int?,
    updateDescription: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = description,
        onValueChange = updateDescription,
        label = { Text(text = stringResource(id = R.string.short_description_label)) },
        supportingText = {
            if (descriptionError == null) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${description.length} / $DESCRIPTION_MAX_LENGTH",
                    textAlign = TextAlign.End,
                )
            } else {
                Text(text = stringResource(id = descriptionError))
            }
        },
        isError = descriptionError != null,
        minLines = 4,
    )
}
