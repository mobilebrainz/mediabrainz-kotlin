package app.mediabrainz.api.response

import app.mediabrainz.api.lookup.LookupResponseInterface
import com.squareup.moshi.Json


data class UrlResponse(
    @field:Json(name = "id") val mbid: String,
    @field:Json(name = "resource") val resource: String,
    //todo: check
    @field:Json(name = "type") val type: String?,
    //inc=...-rels
    @field:Json(name = "relations") val relations: List<RelationResponse>?
) : LookupResponseInterface