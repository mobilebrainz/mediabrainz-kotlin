package app.mediabrainz.api.searchrequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.AreaSearchResponse
import app.mediabrainz.api.response.AreaType
import app.mediabrainz.api.retrofit.SearchRequestService
import app.mediabrainz.api.search.BaseSearchRequest
import app.mediabrainz.api.search.LuceneOperator
import app.mediabrainz.api.search.SearchFieldInterface


/**
 * unconditional search: Area search terms with no fields specified search the AREA and SORTNAME fields.
 *   AreaSearchService().search("France")
 *   AreaSearchService().search("France", 5, 10)
 */
class AreaSearchRequest :
    BaseSearchRequest<AreaSearchResponse, AreaSearchField>() {

    override fun search() = createJsonRetrofitService().searchArea(buildParams())

    fun addType(areaType: AreaType): AreaSearchRequest {
        add(AreaSearchField.TYPE, areaType.type)
        return this
    }

    override fun add(operator: LuceneOperator): AreaSearchRequest {
        super.add(operator)
        return this
    }

}

enum class AreaSearchField(val field: String) : SearchFieldInterface {
    /**
     * the area's MBID
     */
    AID("aid"),

    /**
     * an alias attached to the area
     */
    ALIAS("alias"),

    /**
     * the area's name
     */
    AREA("area"),

    /**
     * the area's begin date
     */
    BEGIN("begin"),

    /**
     * the area's disambiguation comment
     */
    COMMENT("comment"),

    /**
     * the area's end date
     */
    END("end"),

    /**
     * a flag indicating whether or not the area has ended
     */
    ENDED("ended"),

    /**
     * an ISO 3166-1/2/3 code attached to the area
     */
    ISO("iso"),

    /**
     * an ISO 3166-1 code attached to the area
     */
    ISO1("iso1"),

    /**
     * an ISO 3166-2 code attached to the area
     */
    ISO2("iso2"),

    /**
     * an ISO 3166-3 code attached to the area
     */
    ISO3("iso3"),

    /**
     * the area's sort name
     */
    SORTNAME("sortname"),

    /**
     * the area's type
     */
    TYPE("type");

    override fun toString() = field

}