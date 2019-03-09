package app.mediabrainz.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Media(
    @Json(name = "title") val title: String = "",
    @Json(name = "format") val format: String = "",
    @Json(name = "format-id") val formatId: String = "",
    @Json(name = "position") val position: Int = 0,
    @Json(name = "disc-count") val discCount: Int = 0,
    @Json(name = "track-count") val trackCount: Int = 0,
    @Json(name = "track-offset") val trackOffset: Int = 0,
    @Json(name = "tracks") val tracks: List<TrackResponse> = ArrayList(),
    //inc=discs
    @Json(name = "discs") val discs: List<DiscResponse> = ArrayList()
)

// TODO: add formats
enum class Format(val format: String) {
    CD("CD"),
    DIGITAL_MEDIA("Digital Media"),
    VINYL("Vinyl");
}