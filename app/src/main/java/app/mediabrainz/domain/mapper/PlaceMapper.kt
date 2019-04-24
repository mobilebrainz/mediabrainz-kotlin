package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.PlaceResponse
import app.mediabrainz.domain.model.Place


class PlaceMapper {

    fun mapTo(response: PlaceResponse): Place =
        with(response) {
            Place(mbid, name)
        }

}