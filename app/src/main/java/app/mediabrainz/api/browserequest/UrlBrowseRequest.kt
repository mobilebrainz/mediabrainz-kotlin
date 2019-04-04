package app.mediabrainz.api.browserequest

import app.mediabrainz.api.lookupbrowse.BaseBrowseRequest
import app.mediabrainz.api.lookupbrowse.BrowseEmptyIncType
import app.mediabrainz.api.lookupbrowse.BrowseEntityTypeInterface
import app.mediabrainz.api.lookupbrowse.EntityType.RESOURCE_ENTITY
import app.mediabrainz.api.response.UrlResponse


class UrlBrowseRequest(entityType: UrlBrowseEntityType, mbid: String) :
    BaseBrowseRequest<UrlResponse, BrowseEmptyIncType, UrlBrowseEntityType>(entityType, mbid) {

    override fun browse() = createJsonRetrofitService().browseUrl(buildParams())
}

enum class UrlBrowseEntityType(val type: String) : BrowseEntityTypeInterface {
    RESOURCE(RESOURCE_ENTITY);

    override fun toString() = type
}