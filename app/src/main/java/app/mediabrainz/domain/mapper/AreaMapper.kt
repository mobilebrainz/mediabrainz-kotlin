package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.AreaResponse
import app.mediabrainz.api.response.ArtistResponse
import app.mediabrainz.domain.model.Area
import app.mediabrainz.domain.model.Artist


class AreaMapper {

    fun mapTo(response: AreaResponse): Area {
        val area = Area(
            response.id,
            response.name
        )
        return area
    }

    fun mapToList(responseList: List<AreaResponse>): List<Area> {
        val areas = ArrayList<Area>()
        for (response in responseList) {
            areas.add(mapTo(response))
        }
        return areas
    }
}