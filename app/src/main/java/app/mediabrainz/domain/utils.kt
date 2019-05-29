package app.mediabrainz.domain

import java.util.*


fun parenthesesString(str: String) = if (str != "") "($str)" else ""

fun initialCaps(text: String): String {
    val tokenizer = StringTokenizer(text, " ", true)
    val sb = StringBuilder()
    while (tokenizer.hasMoreTokens()) {
        var token = tokenizer.nextToken()
        token = String.format("%s%s", Character.toUpperCase(token[0]), token.substring(1))
        sb.append(token)
    }
    return sb.toString()
}
