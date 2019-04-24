package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.WorkResponse
import app.mediabrainz.domain.model.Work


class WorkMapper {

    fun mapTo(response: WorkResponse): Work =
        with(response) {
            Work(mbid, title)
        }

}