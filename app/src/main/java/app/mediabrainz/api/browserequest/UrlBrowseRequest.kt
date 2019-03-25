package app.mediabrainz.api.browserequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.lookupbrowse.BaseBrowseRequest
import app.mediabrainz.api.lookupbrowse.BrowseEmptyIncType
import app.mediabrainz.api.lookupbrowse.BrowseEntityTypeInterface
import app.mediabrainz.api.lookupbrowse.EntityType.RESOURCE_ENTITY
import app.mediabrainz.api.response.UrlResponse
import app.mediabrainz.api.retrofit.BrowseRequestService


class UrlBrowseRequest(entityType: UrlBrowseEntityType, mbid: String) :
    BaseBrowseRequest<UrlResponse, BrowseEmptyIncType, UrlBrowseEntityType>(entityType, mbid) {

    override fun browse() = WebService
        .createJsonRetrofitService(BrowseRequestService::class.java, Config.WEB_SERVICE)
        .browseUrl(buildParams())
}

enum class UrlBrowseEntityType(val type: String) : BrowseEntityTypeInterface {
    RESOURCE(RESOURCE_ENTITY);

    override fun toString() = type
}