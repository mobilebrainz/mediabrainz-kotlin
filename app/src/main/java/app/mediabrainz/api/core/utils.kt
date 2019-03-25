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

fun getStringFromArray(array: Array<out Any>?, separator: String): String {
    val builder = StringBuilder("")
    if (array != null) {
        for (obj in array) {
            if (obj != "") {
                if (builder.isNotEmpty()) {
                    builder.append(separator)
                }
                builder.append(obj.toString())
            }
        }
    }
    return builder.toString()
}