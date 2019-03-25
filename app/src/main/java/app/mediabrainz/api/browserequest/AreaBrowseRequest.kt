package app.mediabrainz.api.browserequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.lookupbrowse.BaseBrowseRequest
import app.mediabrainz.api.lookupbrowse.BrowseEntityTypeInterface
import app.mediabrainz.api.lookupbrowse.BrowseIncTypeInterface
import app.mediabrainz.api.lookupbrowse.EntityType.COLLECTION_ENTITY
import app.mediabrainz.api.lookupbrowse.IncType.ALIASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.ANNOTATION_INC
import app.mediabrainz.api.lookupbrowse.IncType.TAGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_TAGS_INC
import app.mediabrainz.api.response.AreaBrowseResponse
import app.mediabrainz.api.retrofit.BrowseRequestService


class AreaBrowseRequest(entityType: AreaBrowseEntityType, mbid: String) :
    BaseBrowseRequest<AreaBrowseResponse, AreaBrowseIncType, AreaBrowseEntityType>(entityType, mbid) {

    override fun browse() = WebService
        .createJsonRetrofitService(BrowseRequestService::class.java, Config.WEB_SERVICE)
        .browseArea(buildParams())
}

enum class AreaBrowseEntityType(val type: String) : BrowseEntityTypeInterface {
    COLLECTION(COLLECTION_ENTITY);

    override fun toString() = type
}

enum class AreaBrowseIncType(val inc: String) : BrowseIncTypeInterface {
    ALIASES(ALIASES_INC),
    ANNOTATION(ANNOTATION_INC),
    TAGS(TAGS_INC),
    USER_TAGS(USER_TAGS_INC);

    override fun toString() = inc
}