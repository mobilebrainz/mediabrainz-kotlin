package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.ReleaseResponse
import app.mediabrainz.domain.model.Release


class ReleaseMapper {

    fun mapTo(response: ReleaseResponse): Release =
        with(response) {
            Release(mbid, title)
        }

}