package app.mediabrainz.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class RatingResponse(
    @Json(name = "value") val value: Float = 0.0f,
    @Json(name = "votes-count") val votesCount: Int = 0
)