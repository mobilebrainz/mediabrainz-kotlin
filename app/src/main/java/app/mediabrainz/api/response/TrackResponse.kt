package app.mediabrainz.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class TrackResponse(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String = "",
    @Json(name = "recording") val recording: RecordingResponse? = null,
    @Json(name = "position") val position: Int = 0,
    @Json(name = "length") val length: Long = 0,
    @Json(name = "number") val number: String = ""
)