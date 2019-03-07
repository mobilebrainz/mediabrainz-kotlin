package app.mediabrainz.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * https://musicbrainz.org/doc/Area
 */
@JsonClass(generateAdapter = true)
data class AreaResponse(
    @Json(name = "id") val id: String = "",
    @Json(name = "type") val type: String = "",
    @Json(name = "type-id") val typeId: String = "",
    @Json(name = "score") val score : Int = 0,
    @Json(name = "disambiguation") val disambiguation: String = "",
    @Json(name = "name") val name: String = "",
    @Json(name = "sort-name") val sortName: String = "",
    //@Json(name = "life-span") val lifeSpan: LifeSpanResponse = LifeSpanResponse(),
    @Json(name = "iso-3166-1-codes") val iso1 : List<String> = ArrayList(),
    @Json(name = "iso-3166-2-codes") val iso2 : List<String> = ArrayList(),
    @Json(name = "iso-3166-3-codes") val iso3 : List<String> = ArrayList(),
    //inc=aliases
    @Json(name = "aliases") val aliases: List<AliasResponse> = ArrayList(),
    //inc=annotation
    @Json(name = "annotation") val annotation : String = ""
    //inc=tags
    //@Json(name = "tags") val tags : List<Tag> = ArrayList(),
    //inc=user-tags
    //@Json(name = "user-tags") val userTags : List<Tag> = ArrayList(),
    //inc=...-rels
    //@Json(name = "relations") val relations : List<RelationResponse> = ArrayList();
) : BaseLookupResponse()

enum class AreaType(val type : String) {
    COUNTRY("country"),
    SUBDIVISION("subdivision"),
    COUNTY("county"),
    MUNICIPALITY("municipality"),
    CITY("city"),
    DISTRICT("district"),
    ISLAND("island")
}

@JsonClass(generateAdapter = true)
data class AreaSearchResponse(
    @Json(name = "count") val count: Int = 0,
    @Json(name = "offset") val offset: Int = 0,
    @Json(name = "areas") val areas: List<AreaResponse> = ArrayList()
) : BaseSearchResponse()

@JsonClass(generateAdapter = true)
data class AreaBrowseResponse(
    @Json(name = "area-count") val count: Int = 0,
    @Json(name = "area-offset") val offset: Int = 0,
    @Json(name = "areas") val areas: List<AreaResponse> = ArrayList()
)