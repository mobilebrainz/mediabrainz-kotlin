package app.mediabrainz.api.searchservice

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.retrofit.RetrofitSearchService
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.ArtistSearchResponse
import app.mediabrainz.api.response.ArtistType
import app.mediabrainz.api.search.BaseSearchService
import app.mediabrainz.api.search.LuceneOperator
import app.mediabrainz.api.search.SearchFieldInterface

/**
 * unconditional search: Artist search terms with no fields specified search the ARTIST, SORTNAME and ALIAS fields.
 *    new ArtistSearchService().search("deep purple")
 *    new ArtistSearchService().search("riverside", 5, 10)
 */
class ArtistSearchService :
    BaseSearchService<ArtistSearchResponse, ArtistSearchField>() {

    override fun search() = WebService
        .createJsonRetrofitService(RetrofitSearchService::class.java, Config.WEB_SERVICE)
        .searchArtist(buildParams())

    fun addType(artistType: ArtistType): ArtistSearchService {
        add(ArtistSearchField.TYPE, artistType.toString())
        return this
    }

    override fun add(operator: LuceneOperator): ArtistSearchService {
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