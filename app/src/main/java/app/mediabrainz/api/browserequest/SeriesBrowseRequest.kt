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
import app.mediabrainz.api.response.SeriesBrowseResponse
import app.mediabrainz.api.retrofit.BrowseRequestService


class SeriesBrowseRequest(entityType: SeriesBrowseEntityType, mbid: String) :
    BaseBrowseRequest<SeriesBrowseResponse, SeriesBrowseIncType, SeriesBrowseEntityType>(entityType, mbid) {

    override fun browse() = WebService
        .createJsonRetrofitService(BrowseRequestService::class.java, Config.WEB_SERVICE)
        .browseSeries(buildParams())
}

enum class SeriesBrowseEntityType(val type: String) : BrowseEntityTypeInterface {
    COLLECTION(COLLECTION_ENTITY);

    override fun toString() = type
}

enum class SeriesBrowseIncType(val inc: String) : BrowseIncTypeInterface {
    ALIASES(ALIASES_INC),
    ANNOTATION(ANNOTATION_INC),
    TAGS(TAGS_INC),
    USER_TAGS(USER_TAGS_INC);   //require authorization

    override fun toString() = inc
}