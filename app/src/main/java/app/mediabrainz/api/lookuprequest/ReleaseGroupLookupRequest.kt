package app.mediabrainz.api.lookuprequest

import app.mediabrainz.api.lookupbrowse.BaseLookupRequest
import app.mediabrainz.api.lookupbrowse.IncType.ALIASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.ANNOTATION_INC
import app.mediabrainz.api.lookupbrowse.IncType.ARTISTS_INC
import app.mediabrainz.api.lookupbrowse.IncType.ARTIST_CREDITS_INC
import app.mediabrainz.api.lookupbrowse.IncType.GENRES_INC
import app.mediabrainz.api.lookupbrowse.IncType.RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.RELEASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.TAGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_GENRES_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_TAGS_INC
import app.mediabrainz.api.lookupbrowse.LookupIncTypeInterface
import app.mediabrainz.api.lookupbrowse.LookupParamType
import app.mediabrainz.api.response.ReleaseGroupResponse
import app.mediabrainz.api.response.ReleaseGroupType
import app.mediabrainz.api.response.ReleaseStatusResponse


class ReleaseGroupLookupRequest(mbid: String) :
    BaseLookupRequest<ReleaseGroupResponse, ReleaseGroupLookupIncType>(mbid) {

    override fun lookup() = createJsonRetrofitService().lookupReleaseGroup(mbid, buildParams())

    fun addReleaseGroupType(type: ReleaseGroupType): ReleaseGroupLookupRequest {
        addParam(LookupParamType.TYPE, type.toString().toLowerCase())
        return this
    }

    fun addReleaseStatus(status: ReleaseStatusResponse): ReleaseGroupLookupRequest {
        addParam(LookupParamType.STATUS, status.status)
        return this
    }
}

enum class ReleaseGroupLookupIncType(val inc: String) : LookupIncTypeInterface {
    ARTISTS(ARTISTS_INC),
    RELEASES(RELEASES_INC),
    ARTIST_CREDITS(ARTIST_CREDITS_INC),
    ALIASES(ALIASES_INC),
    ANNOTATION(ANNOTATION_INC),
    TAGS(TAGS_INC),
    GENRES(GENRES_INC),
    RATINGS(RATINGS_INC),
    USER_TAGS(USER_TAGS_INC),             //require authorization
    USER_GENRES(USER_GENRES_INC),         //require authorization
    USER_RATINGS(USER_RATINGS_INC);       //require authorization

    override fun toString() = inc
}