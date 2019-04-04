package app.mediabrainz.api.browserequest

import app.mediabrainz.api.lookupbrowse.BaseBrowseRequest
import app.mediabrainz.api.lookupbrowse.BrowseEntityTypeInterface
import app.mediabrainz.api.lookupbrowse.BrowseIncTypeInterface
import app.mediabrainz.api.lookupbrowse.EntityType.AREA_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.COLLECTION_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.RECORDING_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.RELEASE_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.RELEASE_GROUP_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.WORK_ENTITY
import app.mediabrainz.api.lookupbrowse.IncType.ALIASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.ANNOTATION_INC
import app.mediabrainz.api.lookupbrowse.IncType.GENRES_INC
import app.mediabrainz.api.lookupbrowse.IncType.RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.TAGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_GENRES_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_TAGS_INC
import app.mediabrainz.api.response.ArtistBrowseResponse


class ArtistBrowseRequest(entityType: ArtistBrowseEntityType, mbid: String) :
    BaseBrowseRequest<ArtistBrowseResponse, ArtistBrowseIncType, ArtistBrowseEntityType>(entityType, mbid) {

    override fun browse() = createJsonRetrofitService().browseArtist(buildParams())
}

enum class ArtistBrowseEntityType(val type: String) : BrowseEntityTypeInterface {
    AREA(AREA_ENTITY),
    COLLECTION(COLLECTION_ENTITY),
    RECORDING(RECORDING_ENTITY),
    RELEASE(RELEASE_ENTITY),
    RELEASE_GROUP(RELEASE_GROUP_ENTITY),
    WORK(WORK_ENTITY);

    override fun toString() = type
}

enum class ArtistBrowseIncType(val inc: String) : BrowseIncTypeInterface {
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