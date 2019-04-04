package app.mediabrainz.api.lookuprequest

import app.mediabrainz.api.lookupbrowse.BaseLookupRequest
import app.mediabrainz.api.lookupbrowse.LookupEmptyIncType
import app.mediabrainz.api.response.CollectionResponse


class CollectionLookupRequest(mbid: String) : BaseLookupRequest<CollectionResponse, LookupEmptyIncType>(mbid) {

    override fun lookup() = createJsonRetrofitService().lookupCollection(mbid, buildParams())
}