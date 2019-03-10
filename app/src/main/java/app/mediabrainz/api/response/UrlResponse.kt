package app.mediabrainz.api.response

import app.mediabrainz.api.lookup.LookupResponseInterface
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UrlResponse(
    @Json(name = "id") val mbid: String,
    @Json(name = "resource") val resource: String,
    //todo: check
    @Json(name = "type") val type: String = "",
    //inc=...-rels
    @Json(name = "relations") val relations: List<RelationResponse> = ArrayList()
) : LookupResponseInterface