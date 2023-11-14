package ee.pw.edu.pl.doborpartnera.ui.navigation

import java.net.URLDecoder
import java.nio.charset.StandardCharsets

object NavigationDecoder {
    fun decode(text: String): String =
        URLDecoder.decode(text, StandardCharsets.UTF_8.displayName())
}