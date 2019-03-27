package app.mediabrainz.api.response

import com.squareup.moshi.Json


abstract class BaseItemsResponse<T> {

    @field:Json(name = "count")
    open var count: Int = 0

    @field:Json(name = "offset")
    open var offset: Int = 0

    open var items: List<T> = ArrayList()
}