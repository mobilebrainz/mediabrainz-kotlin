package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.DiscResponse
import app.mediabrainz.domain.model.Disc


class DiscMapper {

    fun mapTo(response: DiscResponse) = with(response) {
        val disc = Disc(id)
        disc
    }

}