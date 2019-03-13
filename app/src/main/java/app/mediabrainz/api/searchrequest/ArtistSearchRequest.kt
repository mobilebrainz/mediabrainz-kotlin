package app.mediabrainz.api.searchrequest

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.retrofit.SearchRequestService
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.ArtistSearchResponse
import app.mediabrainz.api.response.ArtistType
import app.mediabrainz.api.search.BaseSearchRequest
import app.mediabrainz.api.search.LuceneOperator
import app.mediabrainz.api.search.SearchFieldInterface

/**
 * unconditional search: Artist search terms with no fields specified search the ARTIST, SORTNAME and ALIAS fields.
 *    new ArtistSearchRequest().search("deep purple")
 *    new ArtistSearchRequest().search("riverside", 5, 10)
 */
class ArtistSearchRequest :
    BaseSearchRequest<ArtistSearchResponse, ArtistSearchField>() {

    override fun search() = WebService
        .createJsonRetrofitService(SearchRequestService::class.java, Config.WEB_SERVICE)
        .searchArtist(buildParams())

    fun addType(artistType: ArtistType): ArtistSearchRequest {
        add(ArtistSearchField.TYPE, artistType.toString())
        return this
    }

    override fun add(operator: LuceneOperator): ArtistSearchRequest {
        super.add(operator)
        return this
    }

}

enum class ArtistSearchField(val field: String) : SearchFieldInterface {
    ALIAS("alias"),
    AREA("area"),
    ARID("arid"),
    ARTIST("artist"),
    ARTISTACCENT("artistaccent"),
    BEGIN("begin"),
    BEGINAREA("beginarea"),
    COMMENT("comment"),
    COUNTRY("country"),
    END("end"),
    ENDAREA("endarea"),
    ENDED("ended"),
    GENDER("gender"),
    IPI("ipi"),
    SORTNAME("sortname"),
    TAG("tag"),
    TYPE("type");

    override fun toString() = field
}