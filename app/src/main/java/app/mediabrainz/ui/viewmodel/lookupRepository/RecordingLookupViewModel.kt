package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Recording
import app.mediabrainz.domain.repository.lookupRepository.RecordingLookupRepository


class RecordingLookupViewModel : BaseLookupViewModel<Recording>() {

    private val repository = RecordingLookupRepository()

    // retry when error
    override fun lookup() {
        if (mbid != "") {
            repository.lookup(result, mbid)
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelJob()
    }

}