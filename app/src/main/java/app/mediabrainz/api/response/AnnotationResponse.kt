package app.mediabrainz.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * https://musicbrainz.org/doc/Annotation
 */
@JsonClass(generateAdapter = true)
data class AnnotationResponse(
    @Json(name = "entity") val id: String = "",
    @Json(name = "type") val type: String = "",
    @Json(name = "score") val score: Int = 0,
    @Json(name = "name") val name: String = "",
    @Json(name = "text") val text: String = ""
)

@JsonClass(generateAdapter = true)
data class AnnotationSearchResponse(
    @Json(name = "count") val count: Int = 0,
    @Json(name = "offset") val offset: Int = 0,
    @Json(name = "annotations") val annotations: List<Annotation> = ArrayList()
) : BaseSearchResponse()