package app.mediabrainz.api.response

import com.squareup.moshi.Json

data class ReleaseCoverArtResponse(
    @field:Json(name = "release") val release: String,
    @field:Json(name = "images") val images: List<CoverArtImageResponse>?
)

data class CoverArtImageResponse(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "image") val image: String,
    @field:Json(name = "edit") val edit: Long?,
    @field:Json(name = "types") val types: List<String>?,
    @field:Json(name = "front") val front: Boolean?,
    @field:Json(name = "back") val back: Boolean?,
    @field:Json(name = "approved") val approved: Boolean?,
    @field:Json(name = "comment") val comment: String?,
    @field:Json(name = "thumbnails") val thumbnails: ThumbnailsResponse?
)

data class ThumbnailsResponse(
    @field:Json(name = "250") val i250: String?,
    @field:Json(name = "500") val i500: String?,
    @field:Json(name = "1200") val i1200: String?,
    @field:Json(name = "small") val small: String?,
    @field:Json(name = "large") val large: String?
)

enum class CoverArtType (val type: String) {
    FRONT("Front"),
    BACK("Back"),
    BOOKLET("Booklet"),
    MEDIUM("Medium"),
    TRAY("Tray"),
    OBI("Obi"),
    SPINE("Spine"),
    TRACK("Track"),
    LINER("Liner"),
    STICKER("Sticker"),
    POSTER("Poster"),
    WATERMARK("Watermark"),
    OTHER("Other");

    override fun toString() = type
}

enum class CoverArtSize (val size: Int) {
    SMALL_SIZE(250),
    LARGE_SIZE(500),
    ULTRA_LARGE_SIZE(1200)
}