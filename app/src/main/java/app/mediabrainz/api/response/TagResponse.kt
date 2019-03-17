package app.mediabrainz.api.response

import com.squareup.moshi.Json

/**
 * https://musicbrainz.org/doc/Folksonomy_Tagging
 */
data class TagResponse(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "count") val count: Int?,
    @field:Json(name = "score") val score: Int?
)

class TagSearchResponse : BaseSearchResponse<TagResponse>() {
    @field:Json(name = "tags")
    val tags: List<TagResponse> = ArrayList()

    override fun getItems() = tags
}

enum class TagType {
    GENRE, TAG
}