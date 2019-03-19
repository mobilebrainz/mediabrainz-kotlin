package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.ReleaseGroupResponse
import app.mediabrainz.domain.model.ReleaseGroup


class ReleaseGroupMapper {

    fun mapTo(response: ReleaseGroupResponse) = with(response) {
        val rg = ReleaseGroup(mbid, title)
        rg
    }

}