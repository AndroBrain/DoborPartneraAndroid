package ee.pw.edu.pl.data.datasource.auth

import kotlinx.coroutines.flow.StateFlow

typealias Token = String

interface AuthLocalDataSource {
    fun getToken(): StateFlow<Token?>
    fun setToken(token: Token?)
}
