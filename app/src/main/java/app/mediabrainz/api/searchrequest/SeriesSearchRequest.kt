package app.mediabrainz.api.searchrequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.SeriesSearchResponse
import app.mediabrainz.api.response.SeriesType
import app.mediabrainz.api.retrofit.SearchRequestService
import app.mediabrainz.api.search.BaseSearchRequest
import app.mediabrainz.api.search.LuceneOperator
import app.mediabrainz.api.search.SearchFieldInterface

/**
 * unconditional search: Series search terms with no fields specified search the name and alias fields.
 *  SeriesSearchService().search("rock")
 *  SeriesSearchService().search("rock", 5, 10)
 */
class SeriesSearchRequest :
    BaseSearchRequest<SeriesSearchResponse, SeriesSearchField>() {

    override fun search() = WebService
        .createJsonRetrofitService(SearchRequestService::class.java, Config.WEB_SERVICE)
        .searchSeries(buildParams())

    fun addType(type: SeriesType): SeriesSearchRequest {
        add(SeriesSearchField.TYPE, type.type)
        return this
    }

    override fun add(operator: LuceneOperator): SeriesSearchRequest {
        super.add(operator)
        return this
    }

}

enum class SeriesSearchField(val field: String) : SearchFieldInterface {
    /**
     * search field: name
     */
    SERIES("series"),
    ALIAS("alias"),
    TAG("tag"),
    TYPE("type");

    override fun toString() = field
}