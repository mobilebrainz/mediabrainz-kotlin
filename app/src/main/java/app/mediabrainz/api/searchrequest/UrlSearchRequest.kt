package app.mediabrainz.api.searchrequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.UrlSearchResponse
import app.mediabrainz.api.retrofit.SearchRequestService
import app.mediabrainz.api.search.BaseSearchRequest
import app.mediabrainz.api.search.SearchFieldInterface


class UrlSearchRequest :
    BaseSearchRequest<UrlSearchResponse, UrlSearchField>() {

    override fun search() = createJsonRetrofitService().searchUrl(buildParams())
}

enum class UrlSearchField(val field: String) : SearchFieldInterface {
    /**
     * the type of a relationship attached to the URL
     */
    RELATION_TYPE("relationtype"),

    /**
     * the MBID of an entity related to the URL
     */
    TARGET_ID("targetid"),

    /**
     * an entity type related to the URL
     */
    TARGET_TYPE("targettype"),

    /**
     * the URL's MBID
     */
    UID("uid"),

    /**
     * the URL itself
     */
    URL("url");

    override fun toString() = field
}

