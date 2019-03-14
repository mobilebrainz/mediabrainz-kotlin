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
 *    ArtistSearchRequest().search("deep purple")
 *    ArtistSearchRequest().search("riverside", 5, 10)
 */
class ArtistSearchRequest :
    BaseSearchRequest<ArtistSearchResponse, ArtistSearchField>() {

    override fun search() = WebService
        .createJsonRetrofitService(SearchRequestService::class.java, Config.WEB_SERVICE)
        .searchArtist(buildParams())

    fun addType(artistType: ArtistType): ArtistSearchRequest {
        add(ArtistSearchField.TYPE, artistType.type)
        return this
    }

    override fun add(operator: LuceneOperator): ArtistSearchRequest {
        super.add(operator)
        return this
    }

}

enum class ArtistSearchField(val field: String) : SearchFieldInterface {
    /**
     * an alias attached to the artist
     */
    ALIAS("alias"),

    /**
     * the artist's main associated area
     */
    AREA("area"),

    /**
     * the artist's MBID
     */
    ARID("arid"),

    /**
     * the artist's name (without accented characters)
     */
    ARTIST("artist"),

    /**
     * the artist's name (with accented characters)
     */
    ARTISTACCENT("artistaccent"),

    /**
     * the artist's begin date
     */
    BEGIN("begin"),

    /**
     * the artist's begin area
     */
    BEGINAREA("beginarea"),

    /**
     * the artist's disambiguation comment
     */
    COMMENT("comment"),

    /**
     * the 2-letter code (ISO 3166-1 alpha-2) for the artist's main associated country, or “unknown”
     */
    COUNTRY("country"),

    /**
     * the artist's end date
     */
    END("end"),

    /**
     * the artist's end area
     */
    ENDAREA("endarea"),

    /**
     * a flag indicating whether or not the artist has ended
     */
    ENDED("ended"),

    /**
     * the artist's gender (“male”, “female”, or “other”)
     */
    GENDER("gender"),

    /**
     * an IPI code associated with the artist
     */
    IPI("ipi"),

    /**
     * the artist's sort name
     */
    SORTNAME("sortname"),

    /**
     * a tag attached to the artist
     */
    TAG("tag"),

    /**
     * the artist's type (“person”, “group”, ...)
     */
    TYPE("type");

    override fun toString() = field
}