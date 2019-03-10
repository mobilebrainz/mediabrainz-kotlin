package app.mediabrainz.api.response

import app.mediabrainz.api.search.BaseSearchResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeriesResponse(
    @Json(name = "id") val mbid: String,
    @Json(name = "name") val name: String,
    @Json(name = "score") val score: Int = 0,
    @Json(name = "type") val type: String = "",
    @Json(name = "type-id") val typeId: String = "",
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
) : BaseLookupResponse

@JsonClass(generateAdapter = true)
data class SeriesSearchResponse(
    @Json(name = "count") val count: Int = 0,
    @Json(name = "offset") val offset: Int = 0,
    @Json(name = "series") val series: List<SeriesResponse> = ArrayList()
) : BaseSearchResponse

@JsonClass(generateAdapter = true)
data class SeriesBrowseResponse(
    @Json(name = "series-count") val count: Int = 0,
    @Json(name = "series-offset") val offset: Int = 0,
    @Json(name = "series") val series: List<SeriesResponse> = ArrayList()
)

enum class SeriesType(val type: String) {
    RELEASE_GROUP("Release group"),
    RELEASE("Release"),
    RECORDING("Recording"),
    WORK("Work"),
    CATALOQUE("Catalogue"),
    EVENT("Event"),
    TOUR("Tour"),
    FESTIVAL("Festival"),
    RUN("Run");

    override fun toString() = type
}