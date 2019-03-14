package app.mediabrainz.api.searchrequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.CDStubSearchResponse
import app.mediabrainz.api.retrofit.SearchRequestService
import app.mediabrainz.api.search.BaseSearchRequest
import app.mediabrainz.api.search.SearchFieldInterface

/**
 * unconditional search: Query terms without a field specifier will search the ARTIST and TITLE fields.
 *   CDStubSearchService().search("Mac")
 *   CDStubSearchService().search("France", 5, 10)
 */
class CDStubSearchRequest :
    BaseSearchRequest<CDStubSearchResponse, CDStubSearchField>() {

    override fun search() = WebService
        .createJsonRetrofitService(SearchRequestService::class.java, Config.WEB_SERVICE)
        .searchCDStub(buildParams())
}

enum class CDStubSearchField(val field: String) : SearchFieldInterface {
    /**
     * the artist name set on the CD stub
     */
    ARTIST("artist"),

    /**
     * the barcode set on the CD stub
     */
    BARCODE("barcode"),

    /**
     * the comment set on the CD stub
     */
    COMMENT("comment"),

    /**
     * the CD stub's Disc ID
     */
    DISCID("discid"),

    /**
     * the release title set on the CD stub
     */
    TITLE("title"),

    /**
     * the CD stub's number of tracks
     */
    TRACKS("tracks");

    override fun toString() = field

}