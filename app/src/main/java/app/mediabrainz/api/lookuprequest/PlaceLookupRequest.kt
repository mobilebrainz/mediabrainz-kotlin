package app.mediabrainz.api.lookuprequest

import app.mediabrainz.api.lookupbrowse.BaseLookupRequest
import app.mediabrainz.api.lookupbrowse.IncType.ALIASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.ANNOTATION_INC
import app.mediabrainz.api.lookupbrowse.IncType.TAGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_TAGS_INC
import app.mediabrainz.api.lookupbrowse.LookupIncTypeInterface
import app.mediabrainz.api.response.PlaceResponse


class PlaceLookupRequest(mbid: String) : BaseLookupRequest<PlaceResponse, PlaceLookupIncType>(mbid) {

    override fun lookup() = createJsonRetrofitService().lookupPlace(mbid, buildParams())
}

enum class PlaceLookupIncType(val inc: String) : LookupIncTypeInterface {
    ALIASES(ALIASES_INC),
    ANNOTATION(ANNOTATION_INC),
    TAGS(TAGS_INC),
    USER_TAGS(USER_TAGS_INC);         //require authorization

    override fun toString() = inc
}