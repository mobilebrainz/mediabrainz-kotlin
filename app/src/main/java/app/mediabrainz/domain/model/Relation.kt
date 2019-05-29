package app.mediabrainz.domain.model

import app.mediabrainz.domain.initialCaps


class Relation<T>(
    val relation: T,
    var type: String? = null
) {

    fun getPrettyType() =
        if (type != null) {
            initialCaps(type!!.replace('_', ' '))
        } else ""

}