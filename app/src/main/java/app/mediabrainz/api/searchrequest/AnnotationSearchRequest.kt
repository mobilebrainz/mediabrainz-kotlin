package app.mediabrainz.api.searchrequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.AnnotationSearchResponse
import app.mediabrainz.api.retrofit.SearchRequestService
import app.mediabrainz.api.search.BaseSearchRequest
import app.mediabrainz.api.search.SearchFieldInterface


class AnnotationSearchRequest :
    BaseSearchRequest<AnnotationSearchResponse, AnnotationSearchField>() {

    override fun search() = WebService
        .createJsonRetrofitService(SearchRequestService::class.java, Config.WEB_SERVICE)
        .searchAnnotation(buildParams())
}

enum class AnnotationSearchField(val field: String) : SearchFieldInterface {
    NAME("name"),
    ENTITY("entity"),
    TEXT("text"),
    TYPE("type");

    override fun toString() = field
}

