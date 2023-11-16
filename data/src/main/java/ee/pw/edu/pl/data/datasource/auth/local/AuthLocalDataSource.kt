package ee.pw.edu.pl.data.datasource.auth.local

import ee.pw.edu.pl.data.datasource.auth.Token
import kotlinx.coroutines.flow.StateFlow

interface AuthLocalDataSource {
    fun getToken(): StateFlow<Token?>
    fun setToken(token: Token?)
}