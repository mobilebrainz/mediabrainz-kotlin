package app.mediabrainz.api.response

import app.mediabrainz.api.lookup.LookupResponseInterface
import app.mediabrainz.api.search.SearchResponseInterface
import com.squareup.moshi.Json

/**
 * https://musicbrainz.org/doc/Area
 */
data class AreaResponse(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "type-id") val typeId: String?,
    @field:Json(name = "score") val score: Int?,
    @field:Json(name = "disambiguation") val disambiguation: String?,
    @field:Json(name = "sort-name") val sortName: String?,
    @field:Json(name = "life-span") val lifeSpan: LifeSpanResponse?,
    @field:Json(name = "iso-3166-1-codes") val iso1: List<String>?,
    @field:Json(name = "iso-3166-2-codes") val iso2: List<String>?,
    @field:Json(name = "iso-3166-3-codes") val iso3: List<String>?,
    //inc=aliases
    @field:Json(name = "aliases") val aliases: List<AliasResponse>?,
    //inc=annotation
    @field:Json(name = "annotation") val annotation: String?,
    //inc=tags
    @field:Json(name = "tags") val tags : List<TagResponse>?,
    //inc=user-tags
    @field:Json(name = "user-tags") val userTags : List<TagResponse>?,
    //inc=...-rels
    @field:Json(name = "relations") val relations : List<RelationResponse>?
) : LookupResponseInterface

data class AreaSearchResponse(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "offset") val offset: Int,
    @field:Json(name = "areas") val areas: List<AreaResponse>
) : SearchResponseInterface

data class AreaBrowseResponse(
    @field:Json(name = "area-count") val count: Int,
    @field:Json(name = "area-offset") val offset: Int,
    @field:Json(name = "areas") val areas: List<AreaResponse>
)

enum class AreaType(val type: String) {
    COUNTRY("country"),
    SUBDIVISION("subdivision"),
    COUNTY("county"),
    MUNICIPALITY("municipality"),
    CITY("city"),
    DISTRICT("district"),
    ISLAND("island");

    override fun toString() = type
}