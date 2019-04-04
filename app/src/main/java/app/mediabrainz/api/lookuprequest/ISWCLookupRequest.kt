package app.mediabrainz.api.lookuprequest

import app.mediabrainz.api.lookupbrowse.BaseLookupRequest
import app.mediabrainz.api.response.ISWCResponse


class ISWCLookupRequest(mbid: String) : BaseLookupRequest<ISWCResponse, WorkLookupIncType>(mbid) {

    override fun lookup() = createJsonRetrofitService().lookupISWC(mbid, buildParams())
}
