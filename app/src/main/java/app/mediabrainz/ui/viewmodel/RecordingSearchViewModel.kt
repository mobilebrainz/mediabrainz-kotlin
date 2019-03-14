package app.mediabrainz.ui.viewmodel

import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.repository.RecordingSearchRepository


class RecordingSearchViewModel : ViewModel() {

    private val recordingSearchRepository = RecordingSearchRepository()
    val recordingsResource = recordingSearchRepository.mutableLiveData

    private var artistQuery: String = ""
    private var releaseQuery: String = ""
    private var recordingQuery: String = ""
    private var limit: Int = 0
    private var offset: Int = 0

    fun searchRecording(recording: String, limit: Int, offset: Int) {
        searchRecording("", "", recording, limit, offset)
    }

    fun searchRecording(artist: String, release: String, recording: String, limit: Int, offset: Int) {
        if (recordingsResource.value == null
            || artistQuery != artist || releaseQuery != release || recordingQuery != recording
            || this.offset != offset
        ) {
            artistQuery = artist
            releaseQuery = release
            recordingQuery = recording
            this.limit = limit
            this.offset = offset
            searchRecording()
        }
    }

    // retry when error
    fun searchRecording() {
        if (recordingQuery != "" && limit > 0) {
            recordingSearchRepository.search(artistQuery, releaseQuery, recordingQuery, limit, offset)
        }
    }

    override fun onCleared() {
        super.onCleared()
        recordingSearchRepository.cancelJob()
    }

}