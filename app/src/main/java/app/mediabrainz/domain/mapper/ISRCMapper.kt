package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.ISRCResponse
import app.mediabrainz.domain.model.ISRC


class ISRCMapper {

    fun mapTo(response: ISRCResponse) = with(response) {
        val isrc = ISRC(id)
        isrc
    }

}