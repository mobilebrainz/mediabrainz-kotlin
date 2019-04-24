package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.RecordingResponse
import app.mediabrainz.domain.model.Recording


class RecordingMapper {

    fun mapTo(response: RecordingResponse): Recording =
        with(response) {
            Recording(mbid, title)
        }

}