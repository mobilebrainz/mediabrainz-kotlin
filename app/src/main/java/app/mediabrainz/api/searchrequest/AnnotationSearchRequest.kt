package app.mediabrainz.api.searchrequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.AnnotationSearchResponse
import app.mediabrainz.api.retrofit.SearchRequestService
import app.mediabrainz.api.search.BaseSearchRequest
import app.mediabrainz.api.search.SearchFieldInterface


class AnnotationSearchRequest :
    BaseSearchRequest<AnnotationSearchResponse, AnnotationSearchField>() {

    override fun search() = createJsonRetrofitService().searchAnnotation(buildParams())
}

enum class AnnotationSearchField(val field: String) : SearchFieldInterface {
    /**
     * the annotated entity's name or title
     */
    NAME("name"),

    /**
     * the annotated entity's MBID
     */
    ENTITY("entity"),

    /**
     * the annotation's content (includes wiki formatting)
     */
    TEXT("text"),

    /**
     * the annotated entity's entity type
     */
    TYPE("type");

    override fun toString() = field
}

