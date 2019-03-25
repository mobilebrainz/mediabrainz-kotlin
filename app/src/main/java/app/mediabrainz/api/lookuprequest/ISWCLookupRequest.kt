package app.mediabrainz.api.lookuprequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.lookupbrowse.BaseLookupRequest
import app.mediabrainz.api.response.ISWCResponse
import app.mediabrainz.api.retrofit.LookupRequestService


class ISWCLookupRequest(mbid: String) : BaseLookupRequest<ISWCResponse, WorkLookupIncType>(mbid) {

    override fun lookup() = WebService
        .createJsonRetrofitService(LookupRequestService::class.java, Config.WEB_SERVICE)
        .lookupISWC(mbid, buildParams())
}
