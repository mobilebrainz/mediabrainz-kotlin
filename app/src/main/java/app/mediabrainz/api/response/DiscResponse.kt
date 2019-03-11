package app.mediabrainz.api.response

import app.mediabrainz.api.lookup.LookupResponseInterface
import com.squareup.moshi.Json


data class DiscResponse(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "offset-count") val offsetCount: Int?,
    @field:Json(name = "sectors") val sectors: Int?,
    @field:Json(name = "offsets") val offsets: List<Int>?,
    @field:Json(name = "releases") val releases : List<ReleaseResponse>?,
    //inc=...-rels
    @field:Json(name = "relations") val relations: List<RelationResponse>?
) : LookupResponseInterface
