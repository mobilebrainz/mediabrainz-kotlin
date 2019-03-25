package app.mediabrainz.api.browserequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.lookupbrowse.BaseBrowseRequest
import app.mediabrainz.api.lookupbrowse.BrowseEntityTypeInterface
import app.mediabrainz.api.lookupbrowse.BrowseIncTypeInterface
import app.mediabrainz.api.lookupbrowse.EntityType.AREA_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.COLLECTION_ENTITY
import app.mediabrainz.api.lookupbrowse.EntityType.RELEASE_ENTITY
import app.mediabrainz.api.lookupbrowse.IncType.ALIASES_INC
import app.mediabrainz.api.lookupbrowse.IncType.ANNOTATION_INC
import app.mediabrainz.api.lookupbrowse.IncType.RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.TAGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_RATINGS_INC
import app.mediabrainz.api.lookupbrowse.IncType.USER_TAGS_INC
import app.mediabrainz.api.response.LabelBrowseResponse
import app.mediabrainz.api.retrofit.BrowseRequestService


class LabelBrowseRequest(entityType: LabelBrowseEntityType, mbid: String) :
    BaseBrowseRequest<LabelBrowseResponse, LabelBrowseIncType, LabelBrowseEntityType>(entityType, mbid) {

    override fun browse() = WebService
        .createJsonRetrofitService(BrowseRequestService::class.java, Config.WEB_SERVICE)
        .browseLabel(buildParams())
}

enum class LabelBrowseEntityType(val type: String) : BrowseEntityTypeInterface {
    AREA(AREA_ENTITY),
    COLLECTION(COLLECTION_ENTITY),
    RELEASE(RELEASE_ENTITY);

    override fun toString() = type
}

enum class LabelBrowseIncType(val inc: String) : BrowseIncTypeInterface {
    ALIASES(ALIASES_INC),
    ANNOTATION(ANNOTATION_INC),
    TAGS(TAGS_INC),
    RATINGS(RATINGS_INC),
    USER_TAGS(USER_TAGS_INC),         //require authorization
    USER_RATINGS(USER_RATINGS_INC);   //require authorization

    override fun toString() = inc
}