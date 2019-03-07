package app.mediabrainz.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class LinkK(
    @Json(name ="type") val type : String = "",
    @Json(name ="ended") val ended : Boolean = false,
    @Json(name ="url") val url : UrlK = UrlK()
)