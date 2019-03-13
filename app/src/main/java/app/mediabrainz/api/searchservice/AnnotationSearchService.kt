package app.mediabrainz.api.searchservice

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.AnnotationSearchResponse
import app.mediabrainz.api.retrofit.RetrofitSearchService
import app.mediabrainz.api.search.BaseSearchService
import app.mediabrainz.api.search.SearchFieldInterface


class AnnotationSearchService :
    BaseSearchService<AnnotationSearchResponse, AnnotationSearchField>() {

    override fun search() = WebService
        .createJsonRetrofitService(RetrofitSearchService::class.java, Config.WEB_SERVICE)
        .searchAnnotation(buildParams())
}

enum class AnnotationSearchField(val field: String) : SearchFieldInterface {
    NAME("name"),
    ENTITY("entity"),
    TEXT("text"),
    TYPE("type");

    override fun toString() = field
}

