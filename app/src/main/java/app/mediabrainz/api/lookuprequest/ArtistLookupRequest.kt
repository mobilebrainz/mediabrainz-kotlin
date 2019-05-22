package app.mediabrainz.api.lookuprequest

import app.mediabrainz.api.lookupbrowse.BaseLookupRequest
import app.mediabrainz.api.lookupbrowse.IncType.ALIASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.ANNOTATION_INC
import app.mediabrainz.api.lookupbrowse.IncType.GENRES_INC
import app.mediabrainz.api.lookupbrowse.IncType.RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.RECORDINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.RELEASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.RELEASE_GROUPS_INC
import app.mediabrainz.api.lookupbrowse.IncType.TAGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_GENRES_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_TAGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.WORKS_INC
import app.mediabrainz.api.lookupbrowse.LookupIncTypeInterface
import app.mediabrainz.api.lookupbrowse.LookupParamType
import app.mediabrainz.api.response.ArtistResponse
import app.mediabrainz.api.response.ReleaseGroupType
import app.mediabrainz.api.response.ReleaseStatusResponse


class ArtistLookupRequest(mbid: String) : BaseLookupRequest<ArtistResponse, ArtistLookupIncType>(mbid) {

    override fun lookup() = createJsonRetrofitService().lookupArtist(mbid, buildParams())

    fun addReleaseGroupType(type: ReleaseGroupType): ArtistLookupRequest {
        addParam(LookupParamType.TYPE, type.toString().toLowerCase())
        return this
    }

    fun addReleaseStatus(status: ReleaseStatusResponse): ArtistLookupRequest {
        addParam(LookupParamType.STATUS, status.status)
        return this
    }
}

enum class ArtistLookupIncType(val inc: String) : LookupIncTypeInterface {
    ALIASES(ALIASES_INC),
    ANNOTATION(ANNOTATION_INC),
    TAGS(TAGS_INC),
    GENRES(GENRES_INC),
    RATINGS(RATINGS_INC),
    USER_TAGS(USER_TAGS_INC),         //require authorization
    USER_GENRES(USER_GENRES_INC),     //require authorization
    USER_RATINGS(USER_RATINGS_INC),   //require authorization

    RECORDINGS(RECORDINGS_INC),
    RELEASES(RELEASES_INC),
    RELEASE_GROUPS(RELEASE_GROUPS_INC),
    //TODO: various-artists?
    //VARIOUS_ARTISTS(VARIOUS_ARTISTS_INC),
    WORKS(WORKS_INC);

    override fun toString() = inc
}