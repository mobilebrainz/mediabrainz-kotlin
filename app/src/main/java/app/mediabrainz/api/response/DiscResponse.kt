package app.mediabrainz.api.response

import com.squareup.moshi.Json


data class DiscResponse(
    @Json(name = "offset-count") val offsetCount: Int = 0,
    @Json(name = "sectors") val sectors: Int = 0,
    @Json(name = "offsets") val offsets: List<Int> = ArrayList(),
    @Json(name = "releases") val releases : List<ReleaseResponse> = ArrayList()
) : BaseLookupResponse()
