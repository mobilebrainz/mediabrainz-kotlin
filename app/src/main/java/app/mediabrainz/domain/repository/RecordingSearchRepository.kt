package app.mediabrainz.domain.repository

import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.RecordingSearchResponse
import app.mediabrainz.api.searchrequest.RecordingSearchField
import app.mediabrainz.domain.mapper.RecordingMapper
import app.mediabrainz.domain.model.Recording
import app.mediabrainz.domain.parenthesesString


class RecordingSearchRepository : BaseApiRepository<RecordingSearchResponse, List<Recording>>() {

    fun search(recording: String, limit: Int, offset: Int) {
        mutableLiveData.value = Resource.loading()
        recursiveSearch("", "", recording, limit,  offset)
    }

    fun search(artist: String, release: String, recording: String, limit: Int, offset: Int) {
        mutableLiveData.value = Resource.loading()
        recursiveSearch(artist, release, recording, limit,  offset)
    }

    private fun recursiveSearch(artist: String, release: String, recording: String, limit: Int, offset: Int) {
        val request = ApiRequestProvider.createRecordingSearchRequest()
            .add(RecordingSearchField.ARTIST, parenthesesString(artist))
            .add(RecordingSearchField.RELEASE, parenthesesString(release))
            .add(RecordingSearchField.RECORDING, parenthesesString(recording))
            .search(artist, limit, offset)
        call(request,
            { recursiveSearch(artist, release, recording, limit, offset) },
            { RecordingMapper().mapToList(recordings) })
    }

}