package app.mediabrainz.api.lookuprequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.lookupbrowse.BaseLookupRequest
import app.mediabrainz.api.lookupbrowse.LookupEmptyIncType
import app.mediabrainz.api.response.CollectionResponse
import app.mediabrainz.api.retrofit.LookupRequestService


class CollectionLookupRequest(mbid: String) : BaseLookupRequest<CollectionResponse, LookupEmptyIncType>(mbid) {

    override fun lookup() = WebService
        .createJsonRetrofitService(LookupRequestService::class.java, Config.WEB_SERVICE)
        .lookupCollection(mbid, buildParams())
}