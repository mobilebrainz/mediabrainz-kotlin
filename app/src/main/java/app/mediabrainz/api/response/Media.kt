package app.mediabrainz.api.response

import com.squareup.moshi.Json


data class Media(
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "format") val format: String?,
    @field:Json(name = "format-id") val formatId: String?,
    @field:Json(name = "position") val position: Int?,
    @field:Json(name = "disc-count") val discCount: Int?,
    @field:Json(name = "track-count") val trackCount: Int?,
    @field:Json(name = "track-offset") val trackOffset: Int?,
    @field:Json(name = "tracks") val tracks: List<TrackResponse>?,
    //inc=discs
    @field:Json(name = "discs") val discs: List<DiscResponse>?
)

// TODO: add formats
enum class FormatType(val format: String) {
    CD("CD"),
    DIGITAL_MEDIA("Digital Media"),
    VINYL("Vinyl");

    override fun toString() = format
}