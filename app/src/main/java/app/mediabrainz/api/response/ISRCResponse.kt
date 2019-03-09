package app.mediabrainz.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ISRCResponse(
    @Json(name = "id") val id: String,
    @Json(name = "isrc") val isrc: String,
    @Json(name = "recordings") val recordings : List<RecordingResponse> = ArrayList(),
    //inc=...-rels
    @Json(name = "relations") val relations: List<RelationResponse> = ArrayList()
) : BaseLookupResponse()