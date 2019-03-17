package app.mediabrainz.api.response

import com.squareup.moshi.Json


/**
 * https://musicbrainz.org/doc/CD_Stub
 */
data class CDStubResponse(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "artist") val artist: String?,
    @field:Json(name = "score") val score: Int?,
    @field:Json(name = "count") val count: Int?,
    @field:Json(name = "barcode") val barcode: String?,
    @field:Json(name = "comment") val comment: String?
)

class CDStubSearchResponse : BaseSearchResponse<CDStubResponse>() {
    @field:Json(name = "cdstubs")
    val cdstubs: List<CDStubResponse> = ArrayList()

    override fun getItems() = cdstubs
}