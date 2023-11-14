package ee.pw.edu.pl.doborpartnera.ui.screen.auth.login

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
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    navigateUp: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToFillProfile: () -> Unit,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    state.value.errorMsg?.let { errorMsg ->
        val context = LocalContext.current
        LaunchedEffect(errorMsg) {
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
            viewModel.clearErrorMsg()
        }
    }
    val result = state.value.result
    LaunchedEffect(result) {
        when (result) {
            LoginResultDisplayable.PROFILE_FILLED -> {
                navigateToHome()
                viewModel.loggedIn()
            }

            LoginResultDisplayable.PROFILE_UNFILLED -> {
                navigateToFillProfile()
                viewModel.loggedIn()
            }

            null -> Unit
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.login_title))
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
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = App.dimens.views_spacing_medium)
                    .padding(bottom = App.dimens.views_spacing_small),
                value = state.value.email,
                onValueChange = viewModel::changeEmail,
                label = {
                    Text(text = stringResource(id = R.string.email_label))
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                maxLines = 1,
                supportingText = {
                    state.value.emailError?.let { error -> Text(text = stringResource(id = error)) }
                },
                isError = state.value.emailError != null,
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.value.password,
                onValueChange = viewModel::changePassword,
                label = {
                    Text(text = stringResource(id = R.string.password_label))
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        defaultKeyboardAction(ImeAction.Done)
                        viewModel.login()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password,
                ),
                visualTransformation = PasswordVisualTransformation(),
            )
            LoadingButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = App.dimens.views_spacing_medium),
                onClick = viewModel::login,
                isLoading = state.value.isLoading,
            ) {
                Text(text = stringResource(id = R.string.login))
            }
        }
    }
}
