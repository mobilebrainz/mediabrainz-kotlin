package app.mediabrainz.api.response

import app.mediabrainz.api.lookup.LookupResponseInterface
import com.squareup.moshi.Json

data class SeriesResponse(
    @field:Json(name = "id") val mbid: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "score") val score: Int?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "type-id") val typeId: String?,
    @field:Json(name = "disambiguation") val disambiguation: String?,
    //inc=aliases
    @field:Json(name = "aliases") val aliases: List<AliasResponse>?,
    //inc=annotation
    @field:Json(name = "annotation") val annotation: String?,
    //inc=tags
    @field:Json(name = "tags") val tags: List<TagResponse>?,
    //inc=user-tags
    @field:Json(name = "user-tags") val userTags: List<TagResponse>?,
    //inc=...-rels
    @field:Json(name = "relations") val relations: List<RelationResponse>?
) : LookupResponseInterface

class SeriesSearchResponse : BaseSearchResponse<SeriesResponse>() {
    @field:Json(name = "series")
    val series: List<SeriesResponse> = ArrayList()

    override fun getItems() = series
}

data class SeriesBrowseResponse(
    @field:Json(name = "series-count") val count: Int,
    @field:Json(name = "series-offset") val offset: Int,
    @field:Json(name = "series") val series: List<SeriesResponse>
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