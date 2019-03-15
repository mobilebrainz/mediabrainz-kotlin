package app.mediabrainz.domain.repository

import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.RecordingSearchResponse
import app.mediabrainz.api.search.LuceneOperator
import app.mediabrainz.api.search.LuceneOperator.*
import app.mediabrainz.api.searchrequest.RecordingSearchField.*
import app.mediabrainz.domain.mapper.RecordingMapper
import app.mediabrainz.domain.model.Recording
import app.mediabrainz.domain.parenthesesString


class RecordingSearchRepository : BaseApiRepository<RecordingSearchResponse, List<Recording>>() {

    fun search(recording: String, limit: Int, offset: Int) {
        mutableLiveData.value = Resource.loading()
        recursiveSearch("", "", recording, limit, offset)
    }

    fun search(artist: String, release: String, recording: String, limit: Int, offset: Int) {
        mutableLiveData.value = Resource.loading()
        recursiveSearch(artist, release, recording, limit, offset)
    }

    private fun recursiveSearch(artist: String, release: String, recording: String, limit: Int, offset: Int) {
        val deferred = (ApiRequestProvider.createRecordingSearchRequest() +
                (ARTIST to parenthesesString(artist)) +
                (RELEASE to parenthesesString(release)) +
                (RECORDING to parenthesesString(recording)))
            .search(limit, offset)

        /*
        val deferred = ApiRequestProvider.createRecordingSearchRequest()
            .add(ARTIST, parenthesesString(artist))
            .add(RELEASE, parenthesesString(release))
            .add(RECORDING, parenthesesString(recording))
            .search(limit, offset)
        */
        call(deferred,
            { recursiveSearch(artist, release, recording, limit, offset) },
            { RecordingMapper().mapToList(recordings) })
    }

}