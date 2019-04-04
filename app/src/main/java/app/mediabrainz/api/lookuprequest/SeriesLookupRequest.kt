package app.mediabrainz.api.lookuprequest

import app.mediabrainz.api.lookupbrowse.BaseLookupRequest
import app.mediabrainz.api.lookupbrowse.IncType.ALIASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.ANNOTATION_INC
import app.mediabrainz.api.lookupbrowse.IncType.TAGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_TAGS_INC
import app.mediabrainz.api.lookupbrowse.LookupIncTypeInterface
import app.mediabrainz.api.response.SeriesResponse


class SeriesLookupRequest(mbid: String) : BaseLookupRequest<SeriesResponse, SeriesLookupIncType>(mbid) {

    override fun lookup() = createJsonRetrofitService().lookupSeries(mbid, buildParams())
}

enum class SeriesLookupIncType(val inc: String) : LookupIncTypeInterface {
    ALIASES(ALIASES_INC),
    ANNOTATION(ANNOTATION_INC),
    TAGS(TAGS_INC),
    USER_TAGS(USER_TAGS_INC);         //require authorization

    override fun toString() = inc
}