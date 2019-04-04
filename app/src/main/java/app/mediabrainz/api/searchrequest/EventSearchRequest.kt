package app.mediabrainz.api.searchrequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.EventSearchResponse
import app.mediabrainz.api.response.EventType
import app.mediabrainz.api.retrofit.SearchRequestService
import app.mediabrainz.api.search.BaseSearchRequest
import app.mediabrainz.api.search.LuceneOperator
import app.mediabrainz.api.search.SearchFieldInterface

/**
 * unconditional search: Event search terms with no fields specified search the EVENT, SORT_NAME and ALIAS fields.
 *   EventSearchService().search("rock")
 *   EventSearchService().search("rock", 2, 10)
 */
class EventSearchRequest :
    BaseSearchRequest<EventSearchResponse, EventSearchField>() {

    override fun search() = createJsonRetrofitService().searchEvent(buildParams())

    fun addType(type: EventType): EventSearchRequest {
        add(EventSearchField.TYPE, type.toString())
        return this
    }

    override fun add(operator: LuceneOperator): EventSearchRequest {
        super.add(operator)
        return this
    }
}

//TODO: add search fields
enum class EventSearchField(val field: String) : SearchFieldInterface {
    ALIAS("alias"),
    EVENT("event"),
    SORT_NAME("sortname"),
    TAG("tag"),
    TYPE("type");

    override fun toString() = field
}

