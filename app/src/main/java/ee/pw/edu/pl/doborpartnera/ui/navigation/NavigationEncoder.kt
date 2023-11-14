package ee.pw.edu.pl.doborpartnera.ui.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object NavigationEncoder {
    fun encode(text: String): String =
        URLEncoder.encode(text, StandardCharsets.UTF_8.displayName())

    fun encodeNullable(text: String?): String? =
        if (text == null) {
            null
        } else {
            encode(text)
        }
}
