package app.mediabrainz.api.lookuprequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.lookupbrowse.BaseLookupRequest
import app.mediabrainz.api.lookupbrowse.IncType.ALIASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.ANNOTATION_INC
import app.mediabrainz.api.lookupbrowse.IncType.ARTISTS_INC
import app.mediabrainz.api.lookupbrowse.IncType.ARTIST_CREDITS_INC
import app.mediabrainz.api.lookupbrowse.IncType.GENRES_INC
import app.mediabrainz.api.lookupbrowse.IncType.ISRCS_INC
import app.mediabrainz.api.lookupbrowse.IncType.RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.RELEASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.TAGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_GENRES_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_TAGS_INC
import app.mediabrainz.api.lookupbrowse.LookupIncTypeInterface
import app.mediabrainz.api.lookupbrowse.LookupParamType
import app.mediabrainz.api.response.RecordingResponse
import app.mediabrainz.api.response.ReleaseGroupType
import app.mediabrainz.api.response.ReleaseStatus
import app.mediabrainz.api.retrofit.LookupRequestService


class RecordingLookupRequest(mbid: String) : BaseLookupRequest<RecordingResponse, RecordingLookupIncType>(mbid) {

    override fun lookup() = WebService
        .createJsonRetrofitService(LookupRequestService::class.java, Config.WEB_SERVICE)
        .lookupRecording(mbid, buildParams())

    fun addReleaseGroupType(type: ReleaseGroupType): RecordingLookupRequest {
        addParam(LookupParamType.TYPE, type.toString().toLowerCase())
        return this
    }

    fun addReleaseStatus(status: ReleaseStatus): RecordingLookupRequest {
        addParam(LookupParamType.STATUS, status.status)
        return this
    }
}

enum class RecordingLookupIncType(val inc: String) : LookupIncTypeInterface {
    ARTISTS(ARTISTS_INC),
    RELEASES(RELEASES_INC),

    ARTIST_CREDITS(ARTIST_CREDITS_INC), // equal ARTISTS("artists")
    // TODO: ISRCS
    /*
     conflict with return type isrcs field
     search: https://musicbrainz.org/ws/2/recording?fmt=json&query=isrc:USGF19942501
     lookup: https://musicbrainz.org/ws/2/recording/6da76448-982a-4a01-b65b-9a710301c9c9?fmt=json&inc=isrcs
     search return array of objects with fields id = isrc
     lookup ISRCS return array of String isrc
     */
    ISRCS(ISRCS_INC),
    ALIASES(ALIASES_INC),
    ANNOTATION(ANNOTATION_INC),
    GENRES(GENRES_INC),
    TAGS(TAGS_INC),
    RATINGS(RATINGS_INC),
    USER_GENRES(USER_GENRES_INC),     //require authorization
    USER_TAGS(USER_TAGS_INC),         //require authorization
    USER_RATINGS(USER_RATINGS_INC);   //require authorization

    override fun toString() = inc
}