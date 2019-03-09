package app.mediabrainz.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * https://musicbrainz.org/doc/Folksonomy_Tagging
 */
@JsonClass(generateAdapter = true)
data class TagResponse(
    @Json(name = "name") val name: String,
    @Json(name = "count") val count: Int,
    @Json(name = "score") val score: Int
)

@JsonClass(generateAdapter = true)
data class TagSearchResponse(
    @Json(name = "count") val count: Int = 0,
    @Json(name = "offset") val offset: Int = 0,
    @Json(name = "tags") val tags: List<TagResponse> = ArrayList()
) : BaseSearchResponse()

enum class TagType {
    GENRE, TAG
}