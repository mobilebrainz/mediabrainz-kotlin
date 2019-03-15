package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.EventResponse
import app.mediabrainz.domain.model.Event


class EventMapper {

    fun mapTo(response: EventResponse) = with(response) {
        val event = Event(id, name)
        event
    }

    fun mapToList(responseList: List<EventResponse>): List<Event> {
        val events = ArrayList<Event>()
        for (response in responseList) {
            events.add(mapTo(response))
        }
        return events
    }
}