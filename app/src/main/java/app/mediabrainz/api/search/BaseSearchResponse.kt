package app.mediabrainz.api.search

import com.squareup.moshi.Json


abstract class BaseSearchResponse<T> {

    @field:Json(name = "count") var count: Int = 0
    @field:Json(name = "offset") var offset: Int = 0

    abstract fun getItems(): List<T>
}
