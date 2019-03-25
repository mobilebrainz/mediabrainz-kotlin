package app.mediabrainz.api.lookuprequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.lookupbrowse.BaseLookupRequest
import app.mediabrainz.api.lookupbrowse.LookupParamType
import app.mediabrainz.api.response.ISRCResponse
import app.mediabrainz.api.response.ReleaseGroupType
import app.mediabrainz.api.response.ReleaseStatus
import app.mediabrainz.api.retrofit.LookupRequestService


class ISRCLookupRequest(mbid: String) : BaseLookupRequest<ISRCResponse, RecordingLookupIncType>(mbid) {

    override fun lookup() = WebService
        .createJsonRetrofitService(LookupRequestService::class.java, Config.WEB_SERVICE)
        .lookupISRC(mbid, buildParams())

    fun addReleaseGroupType(type: ReleaseGroupType): ISRCLookupRequest {
        addParam(LookupParamType.TYPE, type.toString().toLowerCase())
        return this
    }

    fun addReleaseStatus(status: ReleaseStatus): ISRCLookupRequest {
        addParam(LookupParamType.STATUS, status.status)
        return this
    }
}
