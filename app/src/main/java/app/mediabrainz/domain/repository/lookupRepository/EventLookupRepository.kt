package app.mediabrainz.domain.repository.lookupRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.lookuprequest.EventLookupIncType.USER_RATINGS
import app.mediabrainz.api.lookuprequest.EventLookupIncType.USER_TAGS
import app.mediabrainz.domain.OAuthManager
import app.mediabrainz.domain.mapper.EventMapper
import app.mediabrainz.domain.model.Event
import app.mediabrainz.domain.repository.Resource


class EventLookupRepository : BaseLookupRepository<Event>() {

    override fun lookup(mutableLiveData: MutableLiveData<Resource<Event>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                { ApiRequestProvider.createEventLookupRequest(mbid).lookup() },
                { EventMapper().mapTo(this) })
        }
    }

    override fun authLookup(mutableLiveData: MutableLiveData<Resource<Event>>, mbid: String) {
        if (mbid.isNotBlank()) {
            call(mutableLiveData,
                {
                    ApiRequestProvider.createEventLookupRequest(mbid)
                        .addIncs(USER_TAGS, USER_RATINGS)
                        .addAccessToken(OAuthManager.accessToken?.token ?: "")
                        .lookup()
                },
                { EventMapper().mapTo(this) })
        }
    }

}