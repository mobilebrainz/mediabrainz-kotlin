package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.ArtistCreditResponse
import app.mediabrainz.domain.model.ArtistCredit


class ArtistCreditMapper {

    fun mapTo(response: ArtistCreditResponse): ArtistCredit =
        with(response) {
            ArtistCredit(
                ArtistMapper().mapTo(artist),
                name ?: "",
                joinphrase ?: ""
            )
        }
}