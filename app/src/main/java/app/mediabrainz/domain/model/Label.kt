package app.mediabrainz.domain.model


class Label(
    val mbid: String,
    val name: String
) : Entity() {
}

class LabelInfo(
    val catalogNumber: String,
    val label: Label
) : Entity() {
}