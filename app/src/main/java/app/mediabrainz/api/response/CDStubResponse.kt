package app.mediabrainz.api.response

import app.mediabrainz.api.search.SearchResponseInterface
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 * https://musicbrainz.org/doc/CD_Stub
 */
@JsonClass(generateAdapter = true)
data class CDStubResponse(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String = "",
    @Json(name = "artist") val artist: String = "",
    @Json(name = "score") val score: Int = 0,
    @Json(name = "count") val count: Int = 0,
    @Json(name = "barcode") val barcode: String = "",
    @Json(name = "comment") val comment: String = ""
)

@JsonClass(generateAdapter = true)
data class CDStubSearch(
    @Json(name = "count") val count: Int = 0,
    @Json(name = "offset") val offset: Int = 0,
    @Json(name = "cdstubs") val cdstubs: List<CDStubResponse> = ArrayList()
) : SearchResponseInterface