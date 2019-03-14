package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.EventResponse
import app.mediabrainz.domain.model.Event


class EventMapper {

    fun mapTo(response: EventResponse): Event {
        val event = Event(
            response.id,
            response.name
        )
        return event
    }

    fun mapToList(responseList: List<EventResponse>): List<Event> {
        val events = ArrayList<Event>()
        for (response in responseList) {
            events.add(mapTo(response))
        }
        return events
    }
}