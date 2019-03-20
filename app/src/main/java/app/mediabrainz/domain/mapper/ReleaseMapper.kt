package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.ReleaseResponse
import app.mediabrainz.domain.model.Release


class ReleaseMapper {

    fun mapTo(response: ReleaseResponse) = with(response) {
        val release = Release(mbid, title)
        release
    }

}