package app.mediabrainz.api.service

import app.mediabrainz.api.core.Config
import app.mediabrainz.api.core.RetrofitService
import app.mediabrainz.api.core.WebService
import app.mediabrainz.api.response.ArtistSearchResponse
import app.mediabrainz.api.search.BaseSearchService
import app.mediabrainz.api.search.SearchFieldInterface


class ArtistSearchService :
    BaseSearchService<ArtistSearchResponse, ArtistSearchField>() {

    override fun search() = WebService
        .createJsonRetrofitService(RetrofitService::class.java, Config.WEB_SERVICE)
        .searchArtist(buildParams())

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