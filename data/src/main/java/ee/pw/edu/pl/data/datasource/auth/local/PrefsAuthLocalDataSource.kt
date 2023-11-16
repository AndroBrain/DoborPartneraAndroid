package ee.pw.edu.pl.data.datasource.auth.local

import android.content.SharedPreferences
import androidx.core.content.edit
import ee.pw.edu.pl.data.datasource.auth.Token
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val KEY_TOKEN = "TOKEN"

class PrefsAuthLocalDataSource(
    private val prefs: SharedPreferences,
) : AuthLocalDataSource {

    private val authenticationToken: MutableStateFlow<Token?> = MutableStateFlow(
        prefs.getString(KEY_TOKEN, null)
    )

    override fun getToken(): StateFlow<Token?> = authenticationToken.asStateFlow()

    override fun setToken(token: Token?) {
        prefs.edit {
            if (token == null) {
                remove(KEY_TOKEN)
                authenticationToken.value = null
            } else {
                putString(KEY_TOKEN, token)
                authenticationToken.value = token
            }
        }
    }
}