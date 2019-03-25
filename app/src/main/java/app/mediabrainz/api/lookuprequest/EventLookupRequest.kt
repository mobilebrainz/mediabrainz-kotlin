package app.mediabrainz.api.lookuprequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.lookupbrowse.BaseLookupRequest
import app.mediabrainz.api.lookupbrowse.IncType.ALIASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.ANNOTATION_INC
import app.mediabrainz.api.lookupbrowse.IncType.RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.TAGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_TAGS_INC
import app.mediabrainz.api.lookupbrowse.LookupIncTypeInterface
import app.mediabrainz.api.response.EventResponse
import app.mediabrainz.api.retrofit.LookupRequestService


class EventLookupRequest(mbid: String) : BaseLookupRequest<EventResponse, EventLookupIncType>(mbid) {

    override fun lookup() = WebService
        .createJsonRetrofitService(LookupRequestService::class.java, Config.WEB_SERVICE)
        .lookupEvent(mbid, buildParams())
}

enum class EventLookupIncType(val inc: String) : LookupIncTypeInterface {
    ALIASES(ALIASES_INC),
    ANNOTATION(ANNOTATION_INC),
    TAGS(TAGS_INC),
    RATINGS(RATINGS_INC),
    USER_TAGS(USER_TAGS_INC),         //require authorization
    USER_RATINGS(USER_RATINGS_INC);   //require authorization

    override fun toString() = inc
}