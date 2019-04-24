package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.AreaResponse
import app.mediabrainz.domain.model.Area


class AreaMapper {

    fun mapTo(response: AreaResponse): Area =
        with(response) {
            Area(id, name)
        }

}