package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.ArtistResponse
import app.mediabrainz.domain.model.Artist


class ArtistMapper {

    fun mapTo(response: ArtistResponse): Artist {
        val artist = Artist(
            response.mbid,
            response.name
        )
        return artist
    }

    fun mapToList(responseList: List<ArtistResponse>): List<Artist> {
        val artists = ArrayList<Artist>()
        for (response in responseList) {
            artists.add(mapTo(response))
        }
        return artists
    }
}