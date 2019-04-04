package app.mediabrainz.api.lookuprequest

import app.mediabrainz.api.lookupbrowse.BaseLookupRequest
import app.mediabrainz.api.lookupbrowse.LookupEmptyIncType
import app.mediabrainz.api.response.UrlResponse


class UrlLookupRequest(mbid: String) : BaseLookupRequest<UrlResponse, LookupEmptyIncType>(mbid) {

    override fun lookup() =createJsonRetrofitService().lookupUrl(mbid, buildParams())
}
