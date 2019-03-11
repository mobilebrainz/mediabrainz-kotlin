package app.mediabrainz.api.response

import com.squareup.moshi.Json


data class LifeSpanResponse(
    @field:Json(name = "begin") val begin: String?,
    @field:Json(name = "end") val end: String?,
    @field:Json(name = "ended") val ended: Boolean?
)

