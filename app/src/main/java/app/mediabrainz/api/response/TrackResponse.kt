package app.mediabrainz.api.response

import com.squareup.moshi.Json


data class TrackResponse(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "title") val title: String = "",
    @field:Json(name = "recording") val recording: RecordingResponse?,
    @field:Json(name = "position") val position: Int?,
    @field:Json(name = "length") val length: Long?,
    @field:Json(name = "number") val number: String?
)