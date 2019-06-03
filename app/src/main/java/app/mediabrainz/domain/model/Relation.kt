package app.mediabrainz.domain.model

import app.mediabrainz.domain.initialCaps


class Relation<T>(
    val relation: T,
    var type: String? = null,
    var begin: String = "",
    var end: String = "",
    var ended: Boolean = false,
    var attributes: List<String> = ArrayList()
) {

    fun getPrettyType() =
        if (type != null) {
            initialCaps(type!!.replace('_', ' '))
        } else ""

}