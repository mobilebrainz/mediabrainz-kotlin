package app.mediabrainz.domain.model


class ArtistCredit(
    val artist: Artist,
    val name: String = "",
    val joinphrase: String = ""
) : Entity()
