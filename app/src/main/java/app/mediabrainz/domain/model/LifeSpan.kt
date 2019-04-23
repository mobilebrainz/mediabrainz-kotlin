package app.mediabrainz.domain.model


class LifeSpan(
    val begin: String = "",
    val end: String = "",
    val ended: Boolean = false
) : Entity()