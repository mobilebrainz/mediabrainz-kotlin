package app.mediabrainz.api.searchrequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.LabelSearchResponse
import app.mediabrainz.api.response.LabelType
import app.mediabrainz.api.retrofit.SearchRequestService
import app.mediabrainz.api.search.BaseSearchRequest
import app.mediabrainz.api.search.LuceneOperator
import app.mediabrainz.api.search.SearchFieldInterface


/**
 * unconditional search: Label search terms with no fields specified search the LABEL, SORT_NAME and ALIAS fields.
 *  LabelSearchService().search("Records")
 *  LabelSearchService().search("Records", 2, 10)
 */
class LabelSearchRequest :
    BaseSearchRequest<LabelSearchResponse, LabelSearchField>() {

    override fun search() = createJsonRetrofitService().searchLabel(buildParams())

    fun addType(type: LabelType): LabelSearchRequest {
        add(LabelSearchField.TYPE, type.toString())
        return this
    }

    override fun add(operator: LuceneOperator): LabelSearchRequest {
        super.add(operator)
        return this
    }
}

enum class LabelSearchField(val field: String) : SearchFieldInterface {
    /**
     * the aliases/misspellings for this label
     */
    ALIAS("alias"),

    /**
     * label area
     */
    AREA("area"),

    /**
     * label founding date
     */
    BEGIN("begin"),

    /**
     * label code (only the figures part, i.e. without "LC")
     */
    CODE("code"),

    /**
     * label comment to differentiate similar labels
     */
    COMMENT("comment"),

    /**
     * The two letter country code of the label country
     */
    COUNTRY("country"),

    /**
     * label dissolution date
     */
    END("end"),

    /**
     * true if know ended even if do not know end date
     */
    ENDED("ended"),

    /**
     * ipi
     */
    IPI("ipi"),

    /**
     * label name
     */
    LABEL("label"),

    /**
     * name of the label with any accent characters retained
     */
    LABELACCENT("labelaccent"),

    /**
     * MBID of the label
     */
    LAID("laid"),

    /**
     * label sortname
     */
    SORT_NAME("sortname"),

    /**
     * label type
     */
    TYPE("type"),

    /**
     * folksonomy tag
     */
    TAG("tag");

    override fun toString() = field

}