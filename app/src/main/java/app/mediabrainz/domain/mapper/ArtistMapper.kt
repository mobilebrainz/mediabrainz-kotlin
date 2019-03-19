package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.ArtistResponse
import app.mediabrainz.domain.model.Artist


class ArtistMapper {

    fun mapTo(response: ArtistResponse) = with(response) {
        val artist = Artist(mbid, name)
        artist
    }

}