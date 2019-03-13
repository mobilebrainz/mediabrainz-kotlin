package app.mediabrainz.api.response

import app.mediabrainz.api.search.SearchResponseInterface
import com.squareup.moshi.Json

/**
 * https://musicbrainz.org/doc/Annotation
 */
data class AnnotationResponse(
    @field:Json(name = "entity") val id: String,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "text") val text: String?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "score") val score: Int?
)

data class AnnotationSearchResponse(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "offset") val offset: Int,
    @field:Json(name = "annotations") val annotations: List<AnnotationResponse>
) : SearchResponseInterface