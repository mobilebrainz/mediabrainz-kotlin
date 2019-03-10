package app.mediabrainz.api.response

import app.mediabrainz.api.lookup.LookupResponseInterface
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ISWCResponse(
    @Json(name = "id") val id: String,
    @Json(name = "work-count") val workCount: Int = 0,
    @Json(name = "work-offset") val workOffset: Int = 0,
    @Json(name = "works") val works: List<WorkResponse> = ArrayList(),
    //inc=...-rels
    @Json(name = "relations") val relations: List<RelationResponse> = ArrayList()
) : LookupResponseInterface