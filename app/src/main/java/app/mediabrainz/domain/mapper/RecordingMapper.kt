package app.mediabrainz.domain.mapper

import app.mediabrainz.api.response.RecordingResponse
import app.mediabrainz.domain.model.Recording


class RecordingMapper {

    fun mapTo(response: RecordingResponse) = with(response) {
        val recording = Recording(mbid, title)
        recording
    }

    fun mapToList(responseList: List<RecordingResponse>): List<Recording> {
        val recordings = ArrayList<Recording>()
        for (response in responseList) {
            recordings.add(mapTo(response))
        }
        return recordings
    }
}