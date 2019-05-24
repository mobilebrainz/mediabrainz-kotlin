package app.mediabrainz.api.response

import app.mediabrainz.api.lookupbrowse.BaseLookupEntity
import app.mediabrainz.api.lookupbrowse.BrowseResponseInterface
import app.mediabrainz.api.lookupbrowse.LookupResponseInterface
import com.squareup.moshi.Json

/**
 * lookup example: https://musicbrainz.org/ws/2/collection/0a5c389a-fd5b-4901-83e7-171419318172?fmt=json
 */
data class CollectionResponse(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "editor") val editor: String,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "type-id") val typeId: String?,
    @field:Json(name = "entity-type") val entityType: String?,
    // counts:
    @field:Json(name = "area-count") val areaCount: Int?,
    @field:Json(name = "artist-count") val artistCount: Int?,
    @field:Json(name = "event-count") val eventCount: Int?,
    @field:Json(name = "instrument-count") val instrumentCount: Int?,
    @field:Json(name = "label-count") val labelCount: Int?,
    @field:Json(name = "place-count") val placeCount: Int?,
    @field:Json(name = "recording-count") val recordingCount: Int?,
    @field:Json(name = "release-count") val releaseCount: Int?,
    @field:Json(name = "release-group-count") val releaseGroupCount: Int?,
    @field:Json(name = "series-count") val seriesCount: Int?,
    @field:Json(name = "work-count") val workCount: Int?
) : BaseLookupEntity(), LookupResponseInterface

class CollectionBrowseResponse : BaseItemsResponse<CollectionResponse>(), BrowseResponseInterface {
    @field:Json(name = "collection-count")
    override var count: Int = 0

    @field:Json(name = "collection-offset")
    override var offset: Int = 0

    @field:Json(name = "collections")
    override var items: List<CollectionResponse> = ArrayList()
}

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