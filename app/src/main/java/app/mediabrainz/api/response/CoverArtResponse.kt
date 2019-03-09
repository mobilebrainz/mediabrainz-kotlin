package app.mediabrainz.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CoverArtResponse(
    @Json(name = "back") val back: Boolean = false,
    @Json(name = "darkened") val darkened: Boolean = false,
    @Json(name = "front") val front: Boolean = false,
    @Json(name = "artwork") val artwork: Boolean = false,
    @Json(name = "count") val count: Int = 0
)
