package app.mediabrainz.ui.viewmodel.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Recording
import app.mediabrainz.domain.repository.RecordingSearchRepository
import app.mediabrainz.domain.repository.Resource


class RecordingSearchViewModel : ViewModel() {

    private val recordingSearchRepository = RecordingSearchRepository()
    val recordingsResource: MutableLiveData<Resource<List<Recording>>> = MutableLiveData()

    private var artistQuery: String = ""
    private var releaseQuery: String = ""
    private var recordingQuery: String = ""

    fun searchRecording(recording: String) {
        searchRecording("", "", recording)
    }

    fun searchRecording(artist: String, release: String, recording: String) {
        if (recordingsResource.value == null
            || artistQuery != artist || releaseQuery != release || recordingQuery != recording
        ) {
            this.artistQuery = artist
            this.releaseQuery = release
            this.recordingQuery = recording
            searchRecording()
        }
    }

    // retry when error
    fun searchRecording() {
        if (recordingQuery != "") {
            recordingSearchRepository.search(recordingsResource, artistQuery, releaseQuery, recordingQuery)
        }
    }

    override fun onCleared() {
        super.onCleared()
        recordingSearchRepository.cancelJob()
    }

}