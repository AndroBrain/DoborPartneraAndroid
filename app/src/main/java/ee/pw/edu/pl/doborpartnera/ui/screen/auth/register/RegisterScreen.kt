package ee.pw.edu.pl.doborpartnera.ui.screen.auth.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier,
    ) { insets ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(insets)
                .padding(App.dimens.screen_spacing_medium),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                modifier = Modifier.size(App.dimens.logo_size),
                imageVector = Icons.Default.HeartBroken,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
            Text(
                modifier = Modifier.padding(vertical = App.dimens.views_spacing_small),
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
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
            )
            OutlinedTextField(
                modifier = textFieldModifier,
                value = state.value.password,
                onValueChange = viewModel::changePassword,
                label = {
                    Text(text = stringResource(id = R.string.password_label))
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
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
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                maxLines = 1,
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = App.dimens.views_spacing_medium),
                onClick = viewModel::register,
                enabled = !state.value.isLoading,
            ) {
                Text(text = stringResource(id = R.string.register))
            }
        }
    }
}