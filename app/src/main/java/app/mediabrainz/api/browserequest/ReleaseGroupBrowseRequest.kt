package app.mediabrainz.api.browserequest

import app.mediabrainz.api.core.getStringFromArray
import app.mediabrainz.api.lookupbrowse.BaseBrowseRequest
import app.mediabrainz.api.lookupbrowse.BrowseEntityTypeInterface
import app.mediabrainz.api.lookupbrowse.BrowseIncTypeInterface
import app.mediabrainz.api.lookupbrowse.BrowseParamType
import app.mediabrainz.api.lookupbrowse.EntityType.ARTIST_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.COLLECTION_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.RELEASE_ENTITY
import app.mediabrainz.api.lookupbrowse.IncType.ALIASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.ANNOTATION_INC
import app.mediabrainz.api.lookupbrowse.IncType.ARTIST_CREDITS_INC
import app.mediabrainz.api.lookupbrowse.IncType.GENRES_INC
import app.mediabrainz.api.lookupbrowse.IncType.RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.TAGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_GENRES_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_TAGS_INC
import app.mediabrainz.api.response.ReleaseGroupBrowseResponse
import app.mediabrainz.api.response.ReleaseGroupType


class ReleaseGroupBrowseRequest(entityType: ReleaseGroupBrowseEntityType, mbid: String) :
    BaseBrowseRequest<ReleaseGroupBrowseResponse, ReleaseGroupBrowseIncType, ReleaseGroupBrowseEntityType>(
        entityType, mbid
    ) {

    override fun browse() = createJsonRetrofitService().browseReleaseGroup(buildParams())

    fun addTypes(vararg types: ReleaseGroupType): ReleaseGroupBrowseRequest {
        addParam(BrowseParamType.TYPE, getStringFromArray(types, "|").toLowerCase())
        return this
    }
}

enum class ReleaseGroupBrowseEntityType(val type: String) : BrowseEntityTypeInterface {
    ARTIST(ARTIST_ENTITY),
    COLLECTION(COLLECTION_ENTITY),
    RELEASE(RELEASE_ENTITY);

    override fun toString() = type
}

enum class ReleaseGroupBrowseIncType(val inc: String) : BrowseIncTypeInterface {
    ALIASES(ALIASES_INC),
    ANNOTATION(ANNOTATION_INC),
    TAGS(TAGS_INC),
    GENRES(GENRES_INC),
    RATINGS(RATINGS_INC),
    USER_GENRES(USER_GENRES_INC),     //require authorization
    USER_TAGS(USER_TAGS_INC),             //require authorization
    USER_RATINGS(USER_RATINGS_INC),       //require authorization
    ARTIST_CREDITS(ARTIST_CREDITS_INC);

    override fun toString() = inc
}