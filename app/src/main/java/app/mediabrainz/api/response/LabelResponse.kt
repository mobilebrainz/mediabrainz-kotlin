package app.mediabrainz.api.response

import app.mediabrainz.api.lookupbrowse.BaseLookupEntity
import app.mediabrainz.api.lookupbrowse.BrowseResponseInterface
import app.mediabrainz.api.lookupbrowse.LookupResponseInterface
import app.mediabrainz.api.search.SearchResponseInterface
import com.squareup.moshi.Json


data class LabelResponse(
    @field:Json(name = "id") val mbid: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "sort-name") val sortName: String?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "type-id") val typeId: String?,
    @field:Json(name = "score") val score: Int?,
    @field:Json(name = "country") val country: String?,
    @field:Json(name = "area") val area: AreaResponse?,
    @field:Json(name = "life-span") val lifeSpan: LifeSpanResponse?,
    @field:Json(name = "label-code") val labelCode: String?,
    @field:Json(name = "disambiguation") val disambiguation: String?,
    @field:Json(name = "isnis") val isnis: List<String>?,
    @field:Json(name = "ipis") val ipis: List<String>?,
    /**
     * inc=aliases
     */
    @field:Json(name = "aliases") val aliases: List<AliasResponse>?,
    /**
     * inc=releases
     */
    @field:Json(name = "releases") val releases: List<ReleaseResponse>?,
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
    @field:Json(name = "user-tags") val userTags: List<TagResponse>?
) : BaseLookupEntity(), LookupResponseInterface

class LabelSearchResponse : BaseItemsResponse<LabelResponse>(), SearchResponseInterface {
    @field:Json(name = "labels")
    override var items: List<LabelResponse> = ArrayList()
}

class LabelBrowseResponse : BaseItemsResponse<LabelResponse>(), BrowseResponseInterface {
    @field:Json(name = "label-count")
    override var count: Int = 0

    @field:Json(name = "label-offset")
    override var offset: Int = 0

    @field:Json(name = "labels")
    override var items: List<LabelResponse> = ArrayList()
}

data class LabelInfoResponse(
    @field:Json(name = "catalog-number") val catalogNumber: String?,
    @field:Json(name = "label") val label: LabelResponse
)

enum class LabelType(val type: String) {
    IMPRINT("imprint"),
    PRODUCTION("production"),
    ORIGINAL_PRODUCTION("original production"),
    BOOTLEG_PRODUCTION("bootleg production"),
    REISSUE_PRODUCTION("reissue production"),
    DISTRIBUTOR("distributor"),
    HOLDING("holding"),
    RIGHTS_SOCIETY("rights society");

    override fun toString() = type
}