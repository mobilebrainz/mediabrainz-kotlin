package app.mediabrainz.api.response

import app.mediabrainz.api.lookup.LookupResponseInterface
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * lookup example: https://musicbrainz.org/ws/2/collection/0a5c389a-fd5b-4901-83e7-171419318172?fmt=json
 */
@JsonClass(generateAdapter = true)
data class CollectionResponse(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "editor") val editor: String,
    @Json(name = "type") val type: String = "",
    @Json(name = "type-id") val typeId: String = "",
    @Json(name = "entity-type") val entityType: String = "",
    // counts:
    @Json(name = "area-count") val areaCount: Int = 0,
    @Json(name = "artist-count") val artistCount: Int = 0,
    @Json(name = "event-count") val eventCount: Int = 0,
    @Json(name = "instrument-count") val instrumentCount: Int = 0,
    @Json(name = "label-count") val labelCount: Int = 0,
    @Json(name = "place-count") val placeCount: Int = 0,
    @Json(name = "recording-count") val recordingCount: Int = 0,
    @Json(name = "release-count") val releaseCount: Int = 0,
    @Json(name = "release-group-count") val releaseGroupCount: Int = 0,
    @Json(name = "series-count") val seriesCount: Int = 0,
    @Json(name = "work-count") val workCount: Int = 0,
    //inc=...-rels
    @Json(name = "relations") val relations: List<RelationResponse> = ArrayList()
) : LookupResponseInterface

@JsonClass(generateAdapter = true)
data class CollectionBrowseResponse(
    @Json(name = "collection-count") val count: Int = 0,
    @Json(name = "collection-offset") val offset: Int = 0,
    @Json(name = "collections") val collections: List<CollectionResponse> = ArrayList()
)

enum class CollectionType(val type: String) {
    AREA("Area"),
    ARTIST("Artist"),
    EVENT("Event"),
    ATTENDING_EVENT("Attending"),
    MAYBE_ATTENDING_EVENT("Maybe attending"),
    INSTRUMENT("Instrument"),
    LABEL("Label"),
    PLACE("Place"),
    RECORDING("Recording"),
    RELEASE("Release"),
    OWNED_RELEASE("Owned music"),
    WISHLIST_RELEASE("Wishlist"),
    RELEASE_GROUP("Release group"),
    SERIES("Series"),
    WORK("Work");

    override fun toString() = type
}

enum class CollectionEntityType(val type: String) {
    AREA("area"),
    ARTIST("artist"),
    EVENT("event"),
    INSTRUMENT("instrument"),
    LABEL("label"),
    PLACE("place"),
    RECORDING("recording"),
    RELEASE("release"),
    RELEASE_GROUP("release_group"),
    SERIES("series"),
    WORK("work");

    override fun toString() = type
}