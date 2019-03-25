package app.mediabrainz.api.lookuprequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.lookupbrowse.BaseLookupRequest
import app.mediabrainz.api.lookupbrowse.IncType.ALIASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.ANNOTATION_INC
import app.mediabrainz.api.lookupbrowse.IncType.RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.RELEASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.TAGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_TAGS_INC
import app.mediabrainz.api.lookupbrowse.LookupIncTypeInterface
import app.mediabrainz.api.lookupbrowse.LookupParamType
import app.mediabrainz.api.response.LabelResponse
import app.mediabrainz.api.response.ReleaseGroupType
import app.mediabrainz.api.response.ReleaseStatus
import app.mediabrainz.api.retrofit.LookupRequestService


class LabelLookupRequest(mbid: String) : BaseLookupRequest<LabelResponse, LabelLookupIncType>(mbid) {

    override fun lookup() = WebService
        .createJsonRetrofitService(LookupRequestService::class.java, Config.WEB_SERVICE)
        .lookupLabel(mbid, buildParams())

    fun addReleaseGroupType(type: ReleaseGroupType): LabelLookupRequest {
        addParam(LookupParamType.TYPE, type.toString().toLowerCase())
        return this
    }

    fun addReleaseStatus(status: ReleaseStatus): LabelLookupRequest {
        addParam(LookupParamType.STATUS, status.status)
        return this
    }
}

enum class LabelLookupIncType(val inc: String) : LookupIncTypeInterface {
    RELEASES(RELEASES_INC),
    ALIASES(ALIASES_INC),
    ANNOTATION(ANNOTATION_INC),
    TAGS(TAGS_INC),
    RATINGS(RATINGS_INC),
    USER_TAGS(USER_TAGS_INC),         //require authorization
    USER_RATINGS(USER_RATINGS_INC);   //require authorization

    override fun toString() = inc
}