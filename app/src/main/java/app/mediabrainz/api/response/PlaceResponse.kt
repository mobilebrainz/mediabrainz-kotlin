package app.mediabrainz.api.response

import app.mediabrainz.api.lookup.LookupResponseInterface
import app.mediabrainz.api.search.SearchResponseInterface
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PlaceResponse(
    @Json(name = "id") val mbid: String,
    @Json(name = "name") val name: String,
    @Json(name = "type") val type: String = "",
    @Json(name = "score") val score: Int = 0,
    @Json(name = "address") val address: String = "",
    @Json(name = "coordinates") val coordinate: CoordinateResponse? = null,
    @Json(name = "area") val area: AreaResponse? = null,
    @Json(name = "life-span") val lifeSpan: LifeSpanResponse? = null,
    @Json(name = "disambiguation") val disambiguation: String = "",
    //inc=aliases
    @Json(name = "aliases") val aliases: List<AliasResponse> = ArrayList(),
    //inc=annotation
    @Json(name = "annotation") val annotation: String = "",
    //inc=tags
    @Json(name = "tags") val tags: List<TagResponse> = ArrayList(),
    //inc=user-tags
    @Json(name = "user-tags") val userTags: List<TagResponse> = ArrayList(),
    //inc=...-rels
    @Json(name = "relations") val relations: List<RelationResponse> = ArrayList()
) : LookupResponseInterface

@JsonClass(generateAdapter = true)
data class PlaceSearchResponse(
    @Json(name = "count") val count: Int = 0,
    @Json(name = "offset") val offset: Int = 0,
    @Json(name = "places") val places: List<PlaceResponse> = ArrayList()
) : SearchResponseInterface

@JsonClass(generateAdapter = true)
data class PlaceBrowseResponse(
    @Json(name = "place-count") val count: Int = 0,
    @Json(name = "place-offset") val offset: Int = 0,
    @Json(name = "places") val places: List<PlaceResponse> = ArrayList()
)

@JsonClass(generateAdapter = true)
data class CoordinateResponse(
    @Json(name = "latitude") val latitude: Double = 0.0,
    @Json(name = "longitude") val longitude: Double = 0.0
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