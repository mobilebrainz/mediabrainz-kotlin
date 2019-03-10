package app.mediabrainz.api.response

import app.mediabrainz.api.search.BaseSearchResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class WorkResponse(
    @Json(name = "id") val mbid: String,
    @Json(name = "title") val title: String,
    @Json(name = "score") val score: Int = 0,
    @Json(name = "language") val language: String = "",
    @Json(name = "languages") val languages: List<String> = ArrayList(),
    @Json(name = "disambiguation") val disambiguation: String = "",
    @Json(name = "type") val type: String = "",
    @Json(name = "type-id") val typeId: String = "",
    @Json(name = "iswcs") val iswcs: List<String> = ArrayList(),
    @Json(name = "attributes") val attributes: List<AttributeResponse> = ArrayList(),
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
data class WorkSearchResponse(
    @Json(name = "count") val count: Int = 0,
    @Json(name = "offset") val offset: Int = 0,
    @Json(name = "works") val works: List<WorkResponse> = ArrayList()
) : BaseSearchResponse

@JsonClass(generateAdapter = true)
data class WorkBrowseResponse(
    @Json(name = "work-count") val count: Int = 0,
    @Json(name = "work-offset") val offset: Int = 0,
    @Json(name = "works") val works: List<WorkResponse> = ArrayList()
)

@JsonClass(generateAdapter = true)
data class AttributeResponse(
    @Json(name = "type") val type: String,
    @Json(name = "type-id") val typeId: String,
    @Json(name = "value") val value: String
)