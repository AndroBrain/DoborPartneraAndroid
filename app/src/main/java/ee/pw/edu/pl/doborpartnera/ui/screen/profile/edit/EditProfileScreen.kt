package ee.pw.edu.pl.doborpartnera.ui.screen.profile.edit

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dokar.chiptextfield.Chip
import com.dokar.chiptextfield.ChipTextFieldState
import com.dokar.chiptextfield.m3.ChipTextFieldDefaults
import com.dokar.chiptextfield.m3.OutlinedChipTextField
import com.dokar.chiptextfield.rememberChipTextFieldState
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.components.ErrorText
import ee.pw.edu.pl.doborpartnera.ui.components.LoadingButton
import ee.pw.edu.pl.doborpartnera.ui.components.image.PhotoImage
import ee.pw.edu.pl.doborpartnera.ui.components.image.PhotoImageButton
import ee.pw.edu.pl.doborpartnera.ui.screen.profile.ProfileImage
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: EditProfileViewModel,
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    onSaved: () -> Unit,
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
            onSaved()
        }
    }
    val interestsState =
        rememberChipTextFieldState(chips = state.value.interests.map { Chip(it) })
    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = viewModel::addImages,
    )
    val errorModifier = Modifier.fillMaxWidth()
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = title)
                },
                navigationIcon = navigationIcon,
            )
        }
    ) { insets ->
        val textFieldModifier = Modifier
            .fillMaxWidth()
            .padding(top = App.dimens.views_spacing_small)
        LazyColumn(
            modifier = Modifier
                .padding(insets)
                .padding(horizontal = App.dimens.screen_spacing_medium)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(App.dimens.views_spacing_small),
            contentPadding = PaddingValues(bottom = App.dimens.views_spacing_large),
        ) {
            item {
                Spacer(modifier = Modifier.size(App.dimens.screen_spacing_small))
            }
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.profile_avatar),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            item {
                ProfileImage(
                    url = state.value.avatar.toString(),
                    onImageChanged = viewModel::updateProfileImage,
                    enabled = true
                )
            }
            state.value.avatarError?.let { error ->
                item {
                    ErrorText(modifier = errorModifier, text = stringResource(id = error))
                }
            }
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.profile_your_pictures),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            item {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalArrangement = Arrangement.spacedBy(App.dimens.views_spacing_small),
                ) {
                    state.value.images.forEach { uri ->
                        PhotoImage(
                            url = uri,
                            icon = {
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .size(App.dimens.icon_button_container_size)
                                        .background(
                                            color = MaterialTheme.colorScheme.error,
                                            shape = MaterialTheme.shapes.small,
                                        )
                                        .semantics { role = Role.Button }
                                        .clickable(
                                            onClick = { viewModel.deleteImage(uri) }
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onError,
                                    )
                                }
                            }
                        )
                    }
                    PhotoImageButton(
                        painter = rememberVectorPainter(image = Icons.Default.AddCircle),
                        onClick = {
                            multiplePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                    )
                }
            }
            state.value.imagesError?.let { error ->
                item {
                    ErrorText(modifier = errorModifier, text = stringResource(id = error))
                }
            }
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.short_description_label),
                    style = MaterialTheme.typography.titleMedium,
                )
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
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.profile_interests),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            item {
                InterestsTextField(
                    modifier = textFieldModifier,
                    onSubmit = viewModel::clearInterestsError,
                    state = interestsState,
                )
            }
            item {
                val error = state.value.interestsError
                if (error == null) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = App.dimens.views_spacing_medium),
                        text = "${interestsState.chips.size} / $MAX_INTERESTS",
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.bodySmall,
                    )
                } else {
                    ErrorText(text = stringResource(id = error))
                }
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
            item {
                Spacer(modifier = Modifier.size(App.dimens.screen_spacing_large))
            }
        }
    }
}

@Composable
private fun InterestsTextField(
    modifier: Modifier,
    state: ChipTextFieldState<Chip>,
    onSubmit: () -> Unit,
) {
    OutlinedChipTextField(
        modifier = modifier,
        state = state,
        onSubmit = {
            onSubmit()
            Chip(it)
        },
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
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
