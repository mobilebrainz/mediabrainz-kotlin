package app.mediabrainz.api.response

import app.mediabrainz.api.lookup.LookupResponseInterface
import app.mediabrainz.api.search.SearchResponseInterface
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class LabelResponse(
    @Json(name = "id") val mbid: String,
    @Json(name = "name") val name: String,
    @Json(name = "sort-name") val sortName: String = "",
    @Json(name = "type") val type: String = "",
    @Json(name = "type-id") val typeId: String = "",
    @Json(name = "score") val score: Int = 0,
    @Json(name = "country") val country: String = "",
    @Json(name = "area") val area: AreaResponse? = null,
    @Json(name = "life-span") val lifeSpan: LifeSpanResponse? = null,
    @Json(name = "label-code") val labelCode: String = "",
    @Json(name = "disambiguation") val disambiguation: String = "",
    @Json(name = "isnis") val isnis: List<String> = ArrayList(),
    @Json(name = "ipis") val ipis: List<String> = ArrayList(),
    //inc=aliases
    @Json(name = "aliases") val aliases: List<AliasResponse> = ArrayList(),
    //inc=releases
    @Json(name = "releases") val releases : List<ReleaseResponse> = ArrayList(),
    //inc=annotation
    @Json(name = "annotation") val annotation: String = "",
    //inc=ratings
    @Json(name = "rating") val rating : RatingResponse = RatingResponse(),
    //inc=user-ratings
    @Json(name = "user-rating") val userRating : RatingResponse = RatingResponse(),
    //inc=tags
    @Json(name = "tags") val tags : List<TagResponse> = ArrayList(),
    //inc=user-tags
    @Json(name = "user-tags") val userTags : List<TagResponse> = ArrayList(),
    //inc=...-rels
    @Json(name = "relations") val relations : List<RelationResponse> = ArrayList()
) : LookupResponseInterface

@JsonClass(generateAdapter = true)
data class LabelSearchResponse(
    @Json(name = "count") val count: Int = 0,
    @Json(name = "offset") val offset: Int = 0,
    @Json(name = "labels") val labels: List<LabelResponse> = ArrayList()
) : SearchResponseInterface

@JsonClass(generateAdapter = true)
data class LabelBrowseResponse(
    @Json(name = "label-count") val count: Int = 0,
    @Json(name = "label-offset") val offset: Int = 0,
    @Json(name = "labels") val labels: List<LabelResponse> = ArrayList()
)

@JsonClass(generateAdapter = true)
data class LabelInfoResponse(
    @Json(name = "catalog-number") val catalogNumber: String = "",
    @Json(name = "label") val label: LabelResponse
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