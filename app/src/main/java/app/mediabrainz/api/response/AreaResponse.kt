package app.mediabrainz.api.response

import app.mediabrainz.api.lookupbrowse.BrowseResponseInterface
import app.mediabrainz.api.lookupbrowse.LookupResponseInterface
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
    /**
     * inc=aliases
     */
    @field:Json(name = "aliases") val aliases: List<AliasResponse>?,
    /**
     * inc=annotation
     */
    @field:Json(name = "annotation") val annotation: String?,
    /**
     * inc=tags
     */
    @field:Json(name = "tags") val tags: List<TagResponse>?,
    /**
     * inc=user-tags
     */
    @field:Json(name = "user-tags") val userTags: List<TagResponse>?,
    /**
     * from search requst
     */
    @field:Json(name = "relation-list") val relationList: List<RelationList>?,
    /**
     * inc=...-rels
     */
    @field:Json(name = "relations") val relations: List<RelationResponse>?
) : LookupResponseInterface

class AreaSearchResponse : BaseItemsResponse<AreaResponse>(), SearchResponseInterface {
    @field:Json(name = "areas")
    override var items: List<AreaResponse> = ArrayList()
}

class AreaBrowseResponse : BaseItemsResponse<AreaResponse>(), BrowseResponseInterface {
    @field:Json(name = "area-count")
    override var count: Int = 0

    @field:Json(name = "area-offset")
    override var offset: Int = 0

    @field:Json(name = "areas")
    override var items: List<AreaResponse> = ArrayList()
}

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