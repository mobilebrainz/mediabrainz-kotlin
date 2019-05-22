package app.mediabrainz.api.lookuprequest

import app.mediabrainz.api.lookupbrowse.BaseLookupRequest
import app.mediabrainz.api.lookupbrowse.LookupParamType
import app.mediabrainz.api.response.ISRCResponse
import app.mediabrainz.api.response.ReleaseGroupType
import app.mediabrainz.api.response.ReleaseStatusResponse


class ISRCLookupRequest(mbid: String) : BaseLookupRequest<ISRCResponse, RecordingLookupIncType>(mbid) {

    override fun lookup() = createJsonRetrofitService().lookupISRC(mbid, buildParams())

    fun addReleaseGroupType(type: ReleaseGroupType): ISRCLookupRequest {
        addParam(LookupParamType.TYPE, type.toString().toLowerCase())
        return this
    }

    fun addReleaseStatus(status: ReleaseStatusResponse): ISRCLookupRequest {
        addParam(LookupParamType.STATUS, status.status)
        return this
    }
}
