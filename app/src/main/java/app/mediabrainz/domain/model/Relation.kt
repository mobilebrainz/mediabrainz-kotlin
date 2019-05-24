package app.mediabrainz.domain.model

class Relation<T> (
    val relation: T,
    var type: String? = null
) {
}