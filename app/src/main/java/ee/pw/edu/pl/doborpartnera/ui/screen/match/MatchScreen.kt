package ee.pw.edu.pl.doborpartnera.ui.screen.match

import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ee.pw.edu.pl.doborpartnera.R
import ee.pw.edu.pl.doborpartnera.ui.components.RefreshBox
import ee.pw.edu.pl.doborpartnera.ui.theme.App

@Composable
fun MatchScreen(
    modifier: Modifier = Modifier,
    viewModel: MatchViewModel,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    state.value.errorMsg?.let { errorMsg ->
        val context = LocalContext.current
        LaunchedEffect(errorMsg) {
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
            viewModel.clearErrorMsg()
        }
    }
    val isProfileFilled = state.value.isProfileFilled
    LaunchedEffect(isProfileFilled) {
        if (isProfileFilled) {
//            TODO navigate to full screen matcher and to chats in nested home navigation
        }
    }
    Scaffold(
        modifier = modifier,
    ) { insets ->
        Crossfade(
            modifier = Modifier
                .fillMaxSize()
                .padding(insets),
            targetState = state.value,
        ) { state ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(App.dimens.screen_spacing_medium),
            ) {
                when {
                    state.isInError -> {
                        RefreshBox(
                            modifier = Modifier.fillMaxSize(),
                            isLoading = state.isLoading,
                            onClick = viewModel::loadIsProfileFilled,
                        )
                    }

                    state.isLoading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    else -> {
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(bottom = App.dimens.views_spacing_small)
                                    .size(App.dimens.main_icon_size),
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null,
                            )
                            Text(
                                text = stringResource(id = R.string.match_profile_unfilled),
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }
    }
}