package app.mediabrainz.api.response

import app.mediabrainz.api.lookupbrowse.LookupResponseInterface
import com.squareup.moshi.Json


data class ISRCResponse(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "isrc") val isrc: String,
    @field:Json(name = "recordings") val recordings : List<RecordingResponse>?,
    /**
     * inc=...-rels
     */
    @field:Json(name = "relations") val relations: List<RelationResponse>?
) : LookupResponseInterface