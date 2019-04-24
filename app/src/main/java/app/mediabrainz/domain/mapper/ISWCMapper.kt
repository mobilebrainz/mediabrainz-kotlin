package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.ISWCResponse
import app.mediabrainz.domain.model.ISWC


class ISWCMapper {

    fun mapTo(response: ISWCResponse): ISWC =
        with(response) {
            ISWC(id)
        }

}