package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.AreaResponse
import app.mediabrainz.domain.model.Area


class AreaMapper {

    fun mapTo(response: AreaResponse) = with(response) {
        val area = Area(id, name)
        area
    }

    fun mapToList(responseList: List<AreaResponse>): List<Area> {
        val areas = ArrayList<Area>()
        for (response in responseList) {
            areas.add(mapTo(response))
        }
        return areas
    }
}