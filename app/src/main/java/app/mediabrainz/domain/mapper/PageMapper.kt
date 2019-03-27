package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.BaseItemsResponse
import app.mediabrainz.domain.model.Entities


class PageMapper<IN, OUT>(val map: (IN) -> OUT) {

    fun mapTo(response: BaseItemsResponse<IN>) =
        Entities(response.count, response.offset, mapToList(response.items))

    fun mapToList(responseList: List<IN>): List<OUT> {
        val items = ArrayList<OUT>()
        for (response in responseList) {
            items.add(map.invoke(response))
        }
        return items
    }
}