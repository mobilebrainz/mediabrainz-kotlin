package app.mediabrainz.domain.repository.searchRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.EventResponse
import app.mediabrainz.domain.mapper.EventMapper
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.model.Event
import app.mediabrainz.domain.parenthesesString
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class EventSearchRepository : BaseApiRepository() {

    fun search(mutableLiveData: MutableLiveData<Resource<List<Event>>>, query: String) {
        if (query.isNotBlank()) {
            val limit = 100
            call(mutableLiveData,
                {
                    ApiRequestProvider.createEventSearchRequest()
                        .search(parenthesesString(query), limit, 0)
                },
                {
                    PageMapper<EventResponse, Event> { EventMapper().mapTo(it) }.mapToList(getItems())
                }
            )
        }
    }

}
