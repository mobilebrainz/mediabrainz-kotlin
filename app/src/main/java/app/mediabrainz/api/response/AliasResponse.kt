package app.mediabrainz.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class AliasResponse(
    @Json(name = "name") val name: String = "",
    @Json(name = "sort-name") val sortName: String = "",
    @Json(name = "locale") val locale: String = "",
    @Json(name = "type") val type: String = "",
    @Json(name = "primary") val primary: Boolean = false,
    @Json(name = "begin-date") val beginDate: String = "",
    @Json(name = "end-date") val endDate: String = "",
    @Json(name = "type-id") val typeId: String = ""
)
