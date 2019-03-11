package app.mediabrainz.api.response

import com.squareup.moshi.Json


data class AliasResponse(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "sort-name") val sortName: String?,
    @field:Json(name = "locale") val locale: String?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "type-id") val typeId: String?,
    @field:Json(name = "primary") val primary: Boolean?,
    @field:Json(name = "begin-date") val beginDate: String?,
    @field:Json(name = "end-date") val endDate: String?
)
