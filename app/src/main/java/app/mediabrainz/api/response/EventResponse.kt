package app.mediabrainz.api.response

import app.mediabrainz.api.lookupbrowse.BrowseResponseInterface
import app.mediabrainz.api.lookupbrowse.LookupResponseInterface
import app.mediabrainz.api.search.SearchResponseInterface
import com.squareup.moshi.Json


data class EventResponse(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "cancelled") val cancelled: Boolean?,
    @field:Json(name = "time") val time: String?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "type-id") val typeId: String?,
    @field:Json(name = "disambiguation") val disambiguation: String?,
    @field:Json(name = "setlist") val setlist: String?,
    @field:Json(name = "life-span") val lifeSpan: LifeSpanResponse?,
    /**
     * inc=aliases
     */
    @field:Json(name = "aliases") val aliases: List<AliasResponse>?,
    /**
     * inc=annotation
     */
    @field:Json(name = "annotation") val annotation: String?,
    /**
     * inc=ratings
     */
    @field:Json(name = "rating") val rating: RatingResponse?,
    /**
     * inc=user-ratings
     */
    @field:Json(name = "user-rating") val userRating: RatingResponse?,
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

class EventSearchResponse : BaseItemsResponse<EventResponse>(), SearchResponseInterface {
    @field:Json(name = "events")
    override var items: List<EventResponse> = ArrayList()
}

class EventBrowseResponse : BaseItemsResponse<EventResponse>(), BrowseResponseInterface {
    @field:Json(name = "event-count")
    override var count: Int = 0

    @field:Json(name = "event-offset")
    override var offset: Int = 0

    @field:Json(name = "events")
    override var items: List<EventResponse> = ArrayList()
}

enum class EventType(val type: String) {
    CONCERT("Concert"),
    FESTIVAL("Festival"),
    LAUNCH_EVENT("Launch event"),
    // TODO: check Convention/Expo
    CONVENTION("Convention"),
    EXPO("Expo"),
    // TODO: check Masterclass/Clinic
    MASTERCLASS("Masterclass"),
    CLINIC("Clinic");

    override fun toString() = type
}