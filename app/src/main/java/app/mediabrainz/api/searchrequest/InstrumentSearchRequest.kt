package app.mediabrainz.api.searchrequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.InstrumentSearchResponse
import app.mediabrainz.api.retrofit.SearchRequestService
import app.mediabrainz.api.search.BaseSearchRequest
import app.mediabrainz.api.search.SearchFieldInterface

/**
 * unconditional search:  Instrument terms with no fields specified search the name, description and other fields.
 *   InstrumentSearchService().search("bass")
 *   InstrumentSearchService().search("bass", 2, 10)
 */
class InstrumentSearchRequest :
    BaseSearchRequest<InstrumentSearchResponse, InstrumentSearchField>() {

    override fun search() = createJsonRetrofitService().searchInstrument(buildParams())

}

enum class InstrumentSearchField(val field: String) : SearchFieldInterface {

    /**
     * search only field name
     */
    INSTRUMENT("instrument");

    override fun toString() = field
}