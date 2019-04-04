package app.mediabrainz.api.browserequest

import app.mediabrainz.api.lookupbrowse.BaseBrowseRequest
import app.mediabrainz.api.lookupbrowse.BrowseEntityTypeInterface
import app.mediabrainz.api.lookupbrowse.BrowseIncTypeInterface
import app.mediabrainz.api.lookupbrowse.EntityType.COLLECTION_ENTITY
import app.mediabrainz.api.lookupbrowse.IncType.ALIASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.ANNOTATION_INC
import app.mediabrainz.api.lookupbrowse.IncType.TAGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_TAGS_INC
import app.mediabrainz.api.response.AreaBrowseResponse


class AreaBrowseRequest(entityType: AreaBrowseEntityType, mbid: String) :
    BaseBrowseRequest<AreaBrowseResponse, AreaBrowseIncType, AreaBrowseEntityType>(entityType, mbid) {

    override fun browse() = createJsonRetrofitService().browseArea(buildParams())
}

enum class AreaBrowseEntityType(val type: String) : BrowseEntityTypeInterface {
    COLLECTION(COLLECTION_ENTITY);

    override fun toString() = type
}

enum class AreaBrowseIncType(val inc: String) : BrowseIncTypeInterface {
    ALIASES(ALIASES_INC),
    ANNOTATION(ANNOTATION_INC),
    TAGS(TAGS_INC),
    USER_TAGS(USER_TAGS_INC);   //require authorization

    override fun toString() = inc
}