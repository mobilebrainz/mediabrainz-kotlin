package app.mediabrainz.api.response

import app.mediabrainz.api.lookupbrowse.BaseLookupEntity
import app.mediabrainz.api.lookupbrowse.BrowseResponseInterface
import app.mediabrainz.api.lookupbrowse.LookupResponseInterface
import app.mediabrainz.api.search.SearchResponseInterface
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
    @field:Json(name = "user-tags") val userTags: List<TagResponse>?
) : BaseLookupEntity(), LookupResponseInterface

class PlaceSearchResponse : BaseItemsResponse<PlaceResponse>(), SearchResponseInterface {
    @field:Json(name = "places")
    override var items: List<PlaceResponse> = ArrayList()
}

class PlaceBrowseResponse : BaseItemsResponse<PlaceResponse>(), BrowseResponseInterface {
    @field:Json(name = "place-count")
    override var count: Int = 0

    @field:Json(name = "place-offset")
    override var offset: Int = 0

    @field:Json(name = "places")
    override var items: List<PlaceResponse> = ArrayList()
}

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