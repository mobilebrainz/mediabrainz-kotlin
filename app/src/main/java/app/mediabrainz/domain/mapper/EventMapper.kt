package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.EventResponse
import app.mediabrainz.domain.model.Event


class EventMapper {

    fun mapTo(response: EventResponse): Event =
        with(response) {
            Event(id, name)
        }

}