package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.LabelResponse
import app.mediabrainz.domain.model.Label


class LabelMapper {

    fun mapTo(response: LabelResponse): Label =
        with(response) {
            Label(mbid, name)
        }

}