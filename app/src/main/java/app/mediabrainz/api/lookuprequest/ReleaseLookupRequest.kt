package app.mediabrainz.api.lookuprequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.lookupbrowse.BaseLookupRequest
import app.mediabrainz.api.lookupbrowse.IncType.ALIASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.ANNOTATION_INC
import app.mediabrainz.api.lookupbrowse.IncType.ARTISTS_INC
import app.mediabrainz.api.lookupbrowse.IncType.ARTIST_CREDITS_INC
import app.mediabrainz.api.lookupbrowse.IncType.COLLECTIONS_INC
import app.mediabrainz.api.lookupbrowse.IncType.DISCIDS_INC
import app.mediabrainz.api.lookupbrowse.IncType.GENRES_INC
import app.mediabrainz.api.lookupbrowse.IncType.LABELS_INC
import app.mediabrainz.api.lookupbrowse.IncType.MEDIA_INC
import app.mediabrainz.api.lookupbrowse.IncType.RECORDINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.RELEASE_GROUPS_INC
import app.mediabrainz.api.lookupbrowse.IncType.TAGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_GENRES_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_TAGS_INC
import app.mediabrainz.api.lookupbrowse.LookupIncTypeInterface
import app.mediabrainz.api.lookupbrowse.LookupParamType
import app.mediabrainz.api.response.ReleaseGroupType
import app.mediabrainz.api.response.ReleaseResponse
import app.mediabrainz.api.retrofit.LookupRequestService


class ReleaseLookupRequest(mbid: String) : BaseLookupRequest<ReleaseResponse, ReleaseLookupIncType>(mbid) {

    override fun lookup() = WebService
        .createJsonRetrofitService(LookupRequestService::class.java, Config.WEB_SERVICE)
        .lookupRelease(mbid, buildParams())

    fun addReleaseGroupType(type: ReleaseGroupType): ReleaseLookupRequest {
        addParam(LookupParamType.TYPE, type.toString().toLowerCase())
        return this
    }
}

enum class ReleaseLookupIncType(val inc: String) : LookupIncTypeInterface {
    ARTISTS(ARTISTS_INC),
    COLLECTIONS(COLLECTIONS_INC),
    LABELS(LABELS_INC),
    RECORDINGS(RECORDINGS_INC),
    RELEASE_GROUPS(RELEASE_GROUPS_INC),
    DISCIDS(DISCIDS_INC),
    MEDIA(MEDIA_INC),
    ARTIST_CREDITS(ARTIST_CREDITS_INC), // equal ARTISTS("artists")
    // TODO: user-collections?
    //USER_COLLECTIONS(USER_COLLECTIONS_INC),
    ALIASES(ALIASES_INC),
    ANNOTATION(ANNOTATION_INC),
    GENRES(GENRES_INC),
    TAGS(TAGS_INC),
    USER_GENRES(USER_GENRES_INC),     //require authorization
    USER_TAGS(USER_TAGS_INC);         //require authorization

    override fun toString() = inc
}