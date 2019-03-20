package app.mediabrainz.api.searchrequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.TagSearchResponse
import app.mediabrainz.api.retrofit.SearchRequestService
import app.mediabrainz.api.search.BaseSearchRequest
import app.mediabrainz.api.search.SearchFieldInterface


class TagSearchRequest :
    BaseSearchRequest<TagSearchResponse, TagSearchField>() {

    override fun search() = WebService
        .createJsonRetrofitService(SearchRequestService::class.java, Config.WEB_SERVICE)
        .searchTag(buildParams())
}

enum class TagSearchField(val field: String) : SearchFieldInterface {
    /**
     * the tag's text
     */
    TAG("tag");

    override fun toString() = field
}

