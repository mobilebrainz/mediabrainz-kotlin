package app.mediabrainz.domain.model


class Tag(val name: String): Entity() {

    override fun toString() = name
}
