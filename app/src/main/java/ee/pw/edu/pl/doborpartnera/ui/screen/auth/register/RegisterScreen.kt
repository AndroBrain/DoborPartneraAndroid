package ee.pw.edu.pl.doborpartnera.ui.screen.auth.register

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.components.LoadingButton
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel,
    navigateUp: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    state.value.errorMsg?.let { errorMsg ->
        LaunchedEffect(errorMsg) {
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
            viewModel.clearErrorMsg()
        }
    }
    val isRegistered = state.value.isRegistered
    LaunchedEffect(isRegistered) {
        if (isRegistered) {
            Toast.makeText(context, R.string.register_successful, Toast.LENGTH_SHORT).show()
            navigateToLogin()
            viewModel.registered()
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.register_title))
                },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                }
            )
        },
    ) { insets ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(insets)
                .padding(App.dimens.screen_spacing_medium),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val textFieldModifier = Modifier
                .fillMaxWidth()
                .padding(vertical = App.dimens.views_spacing_extra_small)
            OutlinedTextField(
                modifier = textFieldModifier,
                value = state.value.email,
                onValueChange = viewModel::changeEmail,
                label = {
                    Text(text = stringResource(id = R.string.email_label))
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                supportingText = {
                    state.value.emailError?.let { error -> Text(text = stringResource(id = error)) }
                },
                isError = state.value.emailError != null,
            )
            OutlinedTextField(
                modifier = textFieldModifier,
                value = state.value.name,
                onValueChange = viewModel::changeName,
                label = {
                    Text(text = stringResource(id = R.string.name_label))
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                supportingText = {
                    state.value.nameError?.let { error -> Text(text = stringResource(id = error)) }
                },
                isError = state.value.nameError != null,
            )
            OutlinedTextField(
                modifier = textFieldModifier,
                value = state.value.surname,
                onValueChange = viewModel::changeSurname,
                label = {
                    Text(text = stringResource(id = R.string.surname_label))
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                supportingText = {
                    state.value.surnameError?.let { error -> Text(text = stringResource(id = error)) }
                },
                isError = state.value.surnameError != null,
            )
            OutlinedTextField(
                modifier = textFieldModifier,
                value = state.value.password,
                onValueChange = viewModel::changePassword,
                label = {
                    Text(text = stringResource(id = R.string.password_label))
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password,
                ),
                visualTransformation = PasswordVisualTransformation(),
                supportingText = {
                    state.value.passwordError?.let { error -> Text(text = stringResource(id = error)) }
                },
                isError = state.value.passwordError != null,
            )
            OutlinedTextField(
                modifier = textFieldModifier,
                value = state.value.repeatPassword,
                onValueChange = viewModel::changeRepeatPassword,
                label = {
                    Text(text = stringResource(id = R.string.repeat_password_label))
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        defaultKeyboardAction(ImeAction.Done)
                        viewModel.register()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password,
                ),
                visualTransformation = PasswordVisualTransformation(),
                maxLines = 1,
                supportingText = {
                    state.value.repeatPasswordError?.let { error -> Text(text = stringResource(id = error)) }
                },
                isError = state.value.repeatPasswordError != null,
            )
            LoadingButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = App.dimens.views_spacing_medium),
                onClick = viewModel::register,
                isLoading = state.value.isLoading,
            ) {
                Text(text = stringResource(id = R.string.register))
            }
        }
    }
}