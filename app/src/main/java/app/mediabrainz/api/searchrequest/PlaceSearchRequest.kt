package app.mediabrainz.api.searchrequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.PlaceSearchResponse
import app.mediabrainz.api.response.PlaceType
import app.mediabrainz.api.retrofit.SearchRequestService
import app.mediabrainz.api.search.BaseSearchRequest
import app.mediabrainz.api.search.LuceneOperator
import app.mediabrainz.api.search.SearchFieldInterface

/**
 * unconditional search: Place search terms with no fields specified search the PLACE, ALIAS, ADDRESS and AREA fields.
 *  PlaceSearchService().search("Street")
 *  PlaceSearchService().search("Street", 2, 10)
 */
class PlaceSearchRequest :
    BaseSearchRequest<PlaceSearchResponse, PlaceSearchField>() {

    override fun search() = createJsonRetrofitService().searchPlace(buildParams())

    fun addType(type: PlaceType): PlaceSearchRequest {
        add(PlaceSearchField.TYPE, type.type)
        return this
    }

    override fun add(operator: LuceneOperator): PlaceSearchRequest {
        super.add(operator)
        return this
    }
}

enum class PlaceSearchField(val field: String) : SearchFieldInterface {
    /**
     * the place ID
     */
    PID("pid"),

    /**
     * the address of this place
     */
    ADDRESS("address"),

    /**
     * the aliases/misspellings for this area
     */
    ALIAS("alias"),

    /**
     * area name
     */
    AREA("area"),

    /**
     * place begin date
     */
    BEGIN("begin"),

    /**
     * disambiguation comment
     */
    COMMENT("comment"),

    /**
     * place end date
     */
    END("end"),

    /**
     * place ended
     */
    ENDED("ended"),

    /**
     * place latitude
     */
    LAT("lat"),

    /**
     * place longitude
     */
    LONG("long"),

    /**
     * the places type
     */
    TYPE("type");

    override fun toString() = field
}