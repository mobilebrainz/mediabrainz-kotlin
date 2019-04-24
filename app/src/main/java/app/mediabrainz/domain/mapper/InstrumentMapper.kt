package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.InstrumentResponse
import app.mediabrainz.domain.model.Instrument


class InstrumentMapper {

    fun mapTo(response: InstrumentResponse): Instrument =
        with(response) {
            Instrument(id, name)
        }

}