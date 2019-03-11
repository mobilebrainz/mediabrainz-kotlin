package app.mediabrainz.api.response

import com.squareup.moshi.Json


data class RatingResponse(
    @field:Json(name = "value") val value: Float?,
    @field:Json(name = "votes-count") val votesCount: Int?
)