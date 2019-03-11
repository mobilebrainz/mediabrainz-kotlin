package app.mediabrainz.api.response

import app.mediabrainz.api.lookup.LookupResponseInterface
import com.squareup.moshi.Json


data class ISWCResponse(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "work-count") val workCount: Int?,
    @field:Json(name = "work-offset") val workOffset: Int?,
    @field:Json(name = "works") val works: List<WorkResponse>?,
    //inc=...-rels
    @field:Json(name = "relations") val relations: List<RelationResponse>?
) : LookupResponseInterface