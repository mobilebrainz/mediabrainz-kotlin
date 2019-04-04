package app.mediabrainz.api.searchrequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.TagSearchResponse
import app.mediabrainz.api.retrofit.SearchRequestService
import app.mediabrainz.api.search.BaseSearchRequest
import app.mediabrainz.api.search.SearchFieldInterface

/**
 * Require only digest autorization.
 *  new TagSearchService().search("rock")
 *  new TagSearchService().search("rock", 1, 10)
 */
class TagSearchRequest :
    BaseSearchRequest<TagSearchResponse, TagSearchField>() {

    //todo: make digest autorization!!!
    override fun search() = createJsonRetrofitService().searchTag(buildParams())
}

enum class TagSearchField(val field: String) : SearchFieldInterface {
    /**
     * the tag's text
     */
    TAG("tag");

    override fun toString() = field
}

