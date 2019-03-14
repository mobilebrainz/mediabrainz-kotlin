package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.InstrumentResponse
import app.mediabrainz.domain.model.Instrument


class InstrumentMapper {

    fun mapTo(response: InstrumentResponse): Instrument {
        val instrument = Instrument(
            response.id,
            response.name
        )
        return instrument
    }

    fun mapToList(responseList: List<InstrumentResponse>): List<Instrument> {
        val instruments = ArrayList<Instrument>()
        for (response in responseList) {
            instruments.add(mapTo(response))
        }
        return instruments
    }
}