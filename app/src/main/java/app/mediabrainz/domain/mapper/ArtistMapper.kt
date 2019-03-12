package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.ArtistResponse
import app.mediabrainz.domain.model.Artist

class ArtistMapper {

    fun mapToArtist(artistResponse: ArtistResponse): Artist {
        val artist = Artist(
            artistResponse.mbid,
            artistResponse.name
        )
        return artist
    }

    fun mapToArtists(artistResponseList: List<ArtistResponse>): List<Artist> {
        val artists = ArrayList<Artist>()
        for (artistResponse in artistResponseList) {
            artists.add(mapToArtist(artistResponse))
        }
        return artists
    }
}