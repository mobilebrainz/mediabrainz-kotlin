package app.mediabrainz.api.core

fun getStringFromList(list: List<Any>?, separator: String): String {
    if (list == null || list.isEmpty()) return ""

    val builder = StringBuilder("")
    for (obj in list) {
        if (obj.toString() != "") {
            if (builder.isNotEmpty()) {
                builder.append(separator)
            }
            builder.append(obj.toString())
        }
    }
    return builder.toString()
}