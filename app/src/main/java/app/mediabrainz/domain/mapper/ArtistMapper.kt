package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.ArtistResponse
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.model.ArtistType


class ArtistMapper {

    fun mapTo(response: ArtistResponse) = with(response) {
        val artist = Artist(
            mbid,
            name,
            disambiguation ?: "",
            ArtistType.typeOf(type ?: ""),
            country ?: "",
            gender ?: ""
        )
        artist
    }

}