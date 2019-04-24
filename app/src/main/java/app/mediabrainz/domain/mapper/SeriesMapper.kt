package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.SeriesResponse
import app.mediabrainz.domain.model.Series


class SeriesMapper {

    fun mapTo(response: SeriesResponse): Series =
        with(response) {
            Series(mbid, name)
        }

}