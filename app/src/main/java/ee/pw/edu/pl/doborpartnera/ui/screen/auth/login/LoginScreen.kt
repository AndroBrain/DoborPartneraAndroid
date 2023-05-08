package ee.pw.edu.pl.doborpartnera.ui.screen.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier,
    ) { insets ->
        Column(
            modifier = Modifier
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
                modifier= Modifier.padding(top =App.dimens.views_spacing_small),
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = App.dimens.views_spacing_medium)
                    .padding(bottom = App.dimens.views_spacing_small),
                value = state.value.email,
                onValueChange = viewModel::changeEmail,
                label = {
                    Text(text = stringResource(id = R.string.email_label))
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.value.password,
                onValueChange = viewModel::changePassword,
                label = {
                    Text(text = stringResource(id = R.string.password_label))
                },
                keyboardActions = KeyboardActions(onDone = { viewModel.login() }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = App.dimens.views_spacing_medium),
                onClick = viewModel::login,
                enabled = !state.value.isLoading,
            ) {
                Text(text = stringResource(id = R.string.login))
            }
        }
    }
}
