package ee.pw.edu.pl.doborpartnera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ee.pw.edu.pl.doborpartnera.ui.screen.auth.AuthScreen
import ee.pw.edu.pl.doborpartnera.ui.theme.Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    RegisterScreen(viewModel = hiltViewModel(), navigateUp = {})
//                    LoginScreen(viewModel = hiltViewModel(), navigateUp = {})
                    AuthScreen(navigateToLogin = { /*TODO*/ }, navigateToRegister = { /*TODO*/ })
                }
            }
        }
    }
}
