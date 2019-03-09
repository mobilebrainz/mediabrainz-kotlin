package app.mediabrainz.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class LifeSpanResponse(
    @Json(name = "begin") val begin: String = "",
    @Json(name = "end") val end: String = "",
    @Json(name = "ended") val ended: Boolean = false
)

