package app.mediabrainz.api.response

import app.mediabrainz.api.lookupbrowse.LookupResponseInterface
import com.squareup.moshi.Json


data class PlaceResponse(
    @field:Json(name = "id") val mbid: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "score") val score: Int?,
    @field:Json(name = "address") val address: String?,
    @field:Json(name = "coordinates") val coordinate: CoordinateResponse?,
    @field:Json(name = "area") val area: AreaResponse?,
    @field:Json(name = "life-span") val lifeSpan: LifeSpanResponse?,
    @field:Json(name = "disambiguation") val disambiguation: String?,
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
     * inc=...-rels
     */
    @field:Json(name = "relations") val relations: List<RelationResponse>?
) : LookupResponseInterface

class PlaceSearchResponse : BaseSearchResponse<PlaceResponse>() {
    @field:Json(name = "places")
    val places: List<PlaceResponse> = ArrayList()

    override fun getItems() = places
}

data class PlaceBrowseResponse(
    @field:Json(name = "place-count") val count: Int,
    @field:Json(name = "place-offset") val offset: Int,
    @field:Json(name = "places") val places: List<PlaceResponse>
)

data class CoordinateResponse(
    @field:Json(name = "latitude") val latitude: Double?,
    @field:Json(name = "longitude") val longitude: Double?
)

enum class PlaceType(val type: String) {
    STUDIO("Studio"),
    VENUE("Venue"),
    STADIUM("Stadium"),
    INDOOR_ARENA("Indoor arena"),
    RELIGIOUS_BUILDING("Religious building"),
    OTHER("Other");

    override fun toString() = type
}