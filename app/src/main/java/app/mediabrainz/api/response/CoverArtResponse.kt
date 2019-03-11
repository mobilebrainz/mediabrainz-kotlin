package app.mediabrainz.api.response

import com.squareup.moshi.Json


data class CoverArtResponse(
    @field:Json(name = "back") val back: Boolean?,
    @field:Json(name = "darkened") val darkened: Boolean?,
    @field:Json(name = "front") val front: Boolean?,
    @field:Json(name = "artwork") val artwork: Boolean?,
    @field:Json(name = "count") val count: Int?
)
