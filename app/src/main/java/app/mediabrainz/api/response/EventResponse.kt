package app.mediabrainz.api.response

import app.mediabrainz.api.search.BaseSearchResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class EventResponse(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "cancelled") val cancelled: Boolean = false,
    @Json(name = "time") val time: String = "",
    @Json(name = "type") val type: String = "",
    @Json(name = "type-id") val typeId: String = "",
    @Json(name = "disambiguation") val disambiguation: String = "",
    @Json(name = "setlist") val setlist: String = "",
    @Json(name = "life-span") val lifeSpan: LifeSpanResponse? = null,
    //inc=aliases
    @Json(name = "aliases") val aliases: List<AliasResponse> = ArrayList(),
    //inc=annotation
    @Json(name = "annotation") val annotation: String = "",
    //inc=ratings
    @Json(name = "rating") val rating: RatingResponse = RatingResponse(),
    //inc=user-ratings
    @Json(name = "user-rating") val userRating: RatingResponse = RatingResponse(),
    //inc=tags
    @Json(name = "tags") val tags: List<TagResponse> = ArrayList(),
    //inc=user-tags
    @Json(name = "user-tags") val userTags: List<TagResponse> = ArrayList(),
    //inc=...-rels
    @Json(name = "relations") val relations: List<RelationResponse> = ArrayList()
) : BaseLookupResponse

@JsonClass(generateAdapter = true)
data class EventSearchResponse(
    @Json(name = "count") val count: Int = 0,
    @Json(name = "offset") val offset: Int = 0,
    @Json(name = "events") val events: List<EventResponse> = ArrayList()
) : BaseSearchResponse

@JsonClass(generateAdapter = true)
data class EventBrowseResponse(
    @Json(name = "event-count") val count: Int = 0,
    @Json(name = "event-offset") val offset: Int = 0,
    @Json(name = "events") val events: List<EventResponse> = ArrayList()
)

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