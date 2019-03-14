package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.ArtistResponse
import app.mediabrainz.api.response.ReleaseGroupResponse
import app.mediabrainz.domain.model.Artist
import app.mediabrainz.domain.model.ReleaseGroup


class ReleaseGroupMapper {

    fun mapTo(response: ReleaseGroupResponse): ReleaseGroup {
        val artist = ReleaseGroup(
            response.mbid,
            response.title
        )
        return artist
    }

    fun mapToList(responseList: List<ReleaseGroupResponse>): List<ReleaseGroup> {
        val releaseGroups = ArrayList<ReleaseGroup>()
        for (response in responseList) {
            releaseGroups.add(mapTo(response))
        }
        return releaseGroups
    }
}