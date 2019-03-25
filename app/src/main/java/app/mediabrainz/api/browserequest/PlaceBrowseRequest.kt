package app.mediabrainz.api.browserequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.lookupbrowse.BaseBrowseRequest
import app.mediabrainz.api.lookupbrowse.BrowseEntityTypeInterface
import app.mediabrainz.api.lookupbrowse.BrowseIncTypeInterface
import app.mediabrainz.api.lookupbrowse.EntityType.AREA_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.COLLECTION_ENTITY
import app.mediabrainz.api.lookupbrowse.IncType.ALIASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.ANNOTATION_INC
import app.mediabrainz.api.lookupbrowse.IncType.TAGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_TAGS_INC
import app.mediabrainz.api.response.PlaceBrowseResponse
import app.mediabrainz.api.retrofit.BrowseRequestService


class PlaceBrowseRequest(entityType: PlaceBrowseEntityType, mbid: String) :
    BaseBrowseRequest<PlaceBrowseResponse, PlaceBrowseIncType, PlaceBrowseEntityType>(entityType, mbid) {

    override fun browse() = WebService
        .createJsonRetrofitService(BrowseRequestService::class.java, Config.WEB_SERVICE)
        .browsePlace(buildParams())
}

enum class PlaceBrowseEntityType(val type: String) : BrowseEntityTypeInterface {
    AREA(AREA_ENTITY),
    COLLECTION(COLLECTION_ENTITY);

    override fun toString() = type
}

enum class PlaceBrowseIncType(val inc: String) : BrowseIncTypeInterface {
    ALIASES(ALIASES_INC),
    ANNOTATION(ANNOTATION_INC),
    TAGS(TAGS_INC),
    USER_TAGS(USER_TAGS_INC);         //require authorization

    override fun toString() = inc
}