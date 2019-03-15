package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.ReleaseGroupResponse
import app.mediabrainz.domain.model.ReleaseGroup


class ReleaseGroupMapper {

    fun mapTo(response: ReleaseGroupResponse) = with(response) {
        val rg = ReleaseGroup(mbid, title)
        rg
    }

    fun mapToList(responseList: List<ReleaseGroupResponse>): List<ReleaseGroup> {
        val releaseGroups = ArrayList<ReleaseGroup>()
        for (response in responseList) {
            releaseGroups.add(mapTo(response))
        }
        return releaseGroups
    }
}