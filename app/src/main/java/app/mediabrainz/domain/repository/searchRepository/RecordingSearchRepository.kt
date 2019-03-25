package app.mediabrainz.domain.repository.searchRepository

import androidx.lifecycle.MutableLiveData
import app.mediabrainz.api.ApiRequestProvider
import app.mediabrainz.api.response.RecordingResponse
import app.mediabrainz.api.searchrequest.RecordingSearchField.*
import app.mediabrainz.domain.mapper.PageMapper
import app.mediabrainz.domain.mapper.RecordingMapper
import app.mediabrainz.domain.model.Recording
import app.mediabrainz.domain.parenthesesString
import app.mediabrainz.domain.repository.BaseApiRepository
import app.mediabrainz.domain.repository.Resource


class RecordingSearchRepository : BaseApiRepository() {

    fun search(mutableLiveData: MutableLiveData<Resource<List<Recording>>>, recording: String) {
        search(mutableLiveData,"", "", recording)
    }

    fun search(
        mutableLiveData: MutableLiveData<Resource<List<Recording>>>,
        artist: String, release: String, recording: String
    ) {
        val limit = 100
        val deferred = {
            (ApiRequestProvider.createRecordingSearchRequest() +
                    (ARTIST to parenthesesString(artist)) +
                    (RELEASE to parenthesesString(release)) +
                    (RECORDING to parenthesesString(recording)))
                .search(limit, 0)
        }

        /*
        val deferred = {ApiRequestProvider.createRecordingSearchRequest()
            .add(ARTIST, parenthesesString(artist))
            .add(RELEASE, parenthesesString(release))
            .add(RECORDING, parenthesesString(recording))
            .search(limit, 0)}
        */
        call(mutableLiveData, deferred,
            {
                PageMapper<RecordingResponse, Recording> { RecordingMapper().mapTo(it) }.mapToList(getItems())
            }
        )
    }

}
