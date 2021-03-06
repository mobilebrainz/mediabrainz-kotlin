package app.mediabrainz.api.response

import app.mediabrainz.api.lookupbrowse.BrowseResponseInterface
import app.mediabrainz.api.lookupbrowse.LookupResponseInterface
import app.mediabrainz.api.search.SearchResponseInterface
import com.squareup.moshi.Json


data class WorkResponse(
    @field:Json(name = "id") val mbid: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "score") val score: Int?,
    @field:Json(name = "language") val language: String?,
    @field:Json(name = "languages") val languages: List<String>?,
    @field:Json(name = "disambiguation") val disambiguation: String?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "type-id") val typeId: String?,
    @field:Json(name = "iswcs") val iswcs: List<String>?,
    @field:Json(name = "attributes") val attributes: List<AttributeResponse>?,
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

class WorkSearchResponse : BaseItemsResponse<WorkResponse>(), SearchResponseInterface {
    @field:Json(name = "works")
    override var items: List<WorkResponse> = ArrayList()
}

class WorkBrowseResponse : BaseItemsResponse<WorkResponse>(), BrowseResponseInterface {
    @field:Json(name = "work-count")
    override var count: Int = 0

    @field:Json(name = "work-offset")
    override var offset: Int = 0

    @field:Json(name = "works")
    override var items: List<WorkResponse> = ArrayList()
}

data class AttributeResponse(
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "type-id") val typeId: String?,
    @field:Json(name = "value") val value: String?
)