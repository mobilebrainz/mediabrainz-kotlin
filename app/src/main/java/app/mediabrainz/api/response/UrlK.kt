package app.mediabrainz.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UrlK (
    @Json(name ="id") val mbid : String = "",
    @Json(name ="resource") val resource : String = ""
)
