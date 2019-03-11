package app.mediabrainz.api.response

import app.mediabrainz.api.search.SearchResponseInterface
import com.squareup.moshi.Json

/**
 * https://musicbrainz.org/doc/Folksonomy_Tagging
 */
data class TagResponse(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "count") val count: Int?,
    @field:Json(name = "score") val score: Int?
)

data class TagSearchResponse(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "offset") val offset: Int,
    @field:Json(name = "tags") val tags: List<TagResponse>
) : SearchResponseInterface

enum class TagType {
    GENRE, TAG
}