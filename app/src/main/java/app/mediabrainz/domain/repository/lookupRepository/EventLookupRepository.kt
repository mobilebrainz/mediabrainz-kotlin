package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.domain.mapper.EventMapper
import app.mediabrainz.domain.model.Event
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class EventLookupRepository : BaseApiRepository() {

    fun lookup(mutableLiveData: MutableLiveData<Resource<Event>>, mbid: String) {
        call(mutableLiveData,
            { ApiRequestProvider.createEventLookupRequest(mbid).lookup() },
            { EventMapper().mapTo(this) })
    }

}