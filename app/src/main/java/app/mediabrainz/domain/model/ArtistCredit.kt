package app.mediabrainz.domain.model

import app.mediabrainz.domain.model.Artist


class ArtistCredit(
    val artist: Artist,
    val name: String = "",
    val joinphrase: String = ""
): Entity()
