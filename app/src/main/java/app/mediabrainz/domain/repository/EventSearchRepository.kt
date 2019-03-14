package app.mediabrainz.domain.repository

import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.EventSearchResponse
import app.mediabrainz.domain.mapper.EventMapper
import app.mediabrainz.domain.model.Event
import app.mediabrainz.domain.parenthesesString


class EventSearchRepository : BaseApiRepository<EventSearchResponse, List<Event>>() {

    fun search(query: String, limit: Int, offset: Int) {
        mutableLiveData.value = Resource.loading()
        recursiveSearch(query, limit, offset)
    }

    private fun recursiveSearch(query: String, limit: Int, offset: Int) {
        val request = ApiRequestProvider.createEventSearchRequest()
            .search(parenthesesString(query), limit, offset)
        call(request,
            { recursiveSearch(query, limit, offset) },
            { EventMapper().mapToList(events) })
    }

}