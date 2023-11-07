package ee.pw.edu.pl.data.core.remote

import okhttp3.Request

const val AUTH_HEADER = "Authorization"
const val BEARER = "Bearer"

fun Request.withAccessToken(accessToken: String): Request {
    if (header(AUTH_HEADER) != null) {
        return newBuilder().header(AUTH_HEADER, "$BEARER $accessToken").build()
    }
    return this
}
