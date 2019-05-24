package app.mediabrainz.domain.model


class CoverArtArchive(
    val back: Boolean = false,
    val darkened: Boolean = false,
    val front: Boolean = false,
    val artwork: Boolean = false,
    val count: Int = 0
) : Entity()