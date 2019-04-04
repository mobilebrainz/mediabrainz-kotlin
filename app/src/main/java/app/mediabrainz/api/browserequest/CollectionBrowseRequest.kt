package app.mediabrainz.api.browserequest

import app.mediabrainz.api.lookupbrowse.BaseBrowseRequest
import app.mediabrainz.api.lookupbrowse.BrowseEntityTypeInterface
import app.mediabrainz.api.lookupbrowse.BrowseIncTypeInterface
import app.mediabrainz.api.lookupbrowse.EntityType.AREA_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.ARTIST_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.EDITOR_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.EVENT_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.LABEL_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.PLACE_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.RECORDING_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.RELEASE_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.RELEASE_GROUP_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.WORK_ENTITY
import app.mediabrainz.api.lookupbrowse.IncType.USER_COLLECTIONS_INC
import app.mediabrainz.api.response.CollectionBrowseResponse


class CollectionBrowseRequest(entityType: CollectionBrowseEntityType, mbid: String) :
    BaseBrowseRequest<CollectionBrowseResponse, CollectionBrowseIncType, CollectionBrowseEntityType>(entityType, mbid) {

    override fun browse() = createJsonRetrofitService().browseCollection(buildParams())
}

enum class CollectionBrowseEntityType(val type: String) : BrowseEntityTypeInterface {
    AREA(AREA_ENTITY),
    ARTIST(ARTIST_ENTITY),
    EDITOR(EDITOR_ENTITY),
    EVENT(EVENT_ENTITY),
    LABEL(LABEL_ENTITY),
    PLACE(PLACE_ENTITY),
    RECORDING(RECORDING_ENTITY),
    RELEASE(RELEASE_ENTITY),
    RELEASE_GROUP(RELEASE_GROUP_ENTITY),
    WORK(WORK_ENTITY);

    override fun toString() = type
}

enum class CollectionBrowseIncType(val inc: String) : BrowseIncTypeInterface {
    USER_COLLECTIONS(USER_COLLECTIONS_INC);   //require authorization

    override fun toString() = inc
}