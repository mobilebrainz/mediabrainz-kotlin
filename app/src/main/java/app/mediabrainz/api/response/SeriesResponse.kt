package app.mediabrainz.api.response

import app.mediabrainz.api.lookupbrowse.BaseLookupEntity
import app.mediabrainz.api.lookupbrowse.BrowseResponseInterface
import app.mediabrainz.api.lookupbrowse.LookupResponseInterface
import app.mediabrainz.api.search.SearchResponseInterface
import com.squareup.moshi.Json

data class SeriesResponse(
    @field:Json(name = "id") val mbid: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "score") val score: Int?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "type-id") val typeId: String?,
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

class SeriesSearchResponse : BaseItemsResponse<SeriesResponse>(), SearchResponseInterface {
    @field:Json(name = "series")
    override var items: List<SeriesResponse> = ArrayList()
}

class SeriesBrowseResponse : BaseItemsResponse<SeriesResponse>(), BrowseResponseInterface {
    @field:Json(name = "series-count")
    override var count: Int = 0

    @field:Json(name = "series-offset")
    override var offset: Int = 0

    @field:Json(name = "series")
    override var items: List<SeriesResponse> = ArrayList()
}

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