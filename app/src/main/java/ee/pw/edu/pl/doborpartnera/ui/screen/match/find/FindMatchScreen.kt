package ee.pw.edu.pl.doborpartnera.ui.screen.match.find

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun FindMatchScreen(
    modifier: Modifier = Modifier,
    viewModel: FindMatchViewModel,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Scaffold(modifier = modifier) { insets ->
        Box(modifier = Modifier.padding(insets)) {

        }
    }
}
