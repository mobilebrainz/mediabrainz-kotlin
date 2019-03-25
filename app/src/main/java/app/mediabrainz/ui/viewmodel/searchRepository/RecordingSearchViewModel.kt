package app.mediabrainz.ui.viewmodel.searchRepository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.mediabrainz.domain.model.Recording
import app.mediabrainz.domain.repository.searchRepository.RecordingSearchRepository
import app.mediabrainz.domain.repository.Resource


class RecordingSearchViewModel : ViewModel() {

    private val repository = RecordingSearchRepository()
    val result: MutableLiveData<Resource<List<Recording>>> = MutableLiveData()

    private var artistQuery: String = ""
    private var releaseQuery: String = ""
    private var recordingQuery: String = ""

    fun search(recording: String) {
        search("", "", recording)
    }

    fun search(artist: String, release: String, recording: String) {
        if (result.value == null
            || artistQuery != artist || releaseQuery != release || recordingQuery != recording
        ) {
            this.artistQuery = artist
            this.releaseQuery = release
            this.recordingQuery = recording
            search()
        }
    }

    // retry when error
    fun search() {
        if (recordingQuery != "") {
            repository.search(result, artistQuery, releaseQuery, recordingQuery)
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelJob()
    }

}