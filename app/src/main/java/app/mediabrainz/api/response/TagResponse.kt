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

class TagSearchResponse : BaseItemsResponse<TagResponse>(), SearchResponseInterface {
    @field:Json(name = "tags")
    override var items: List<TagResponse> = ArrayList()
}

enum class TagTypeResponse {
    GENRE, TAG
}