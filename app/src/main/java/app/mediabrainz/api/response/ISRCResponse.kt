package app.mediabrainz.api.response

import app.mediabrainz.api.lookupbrowse.BaseLookupEntity
import app.mediabrainz.api.lookupbrowse.LookupResponseInterface
import com.squareup.moshi.Json


data class ISRCResponse(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "isrc") val isrc: String,
    @field:Json(name = "recordings") val recordings : List<RecordingResponse>?
) : BaseLookupEntity(), LookupResponseInterface