package app.mediabrainz.api.response

import com.squareup.moshi.Json


data class RelationList(
    @field:Json(name = "relations") val relations: List<RelationResponse>?
)
