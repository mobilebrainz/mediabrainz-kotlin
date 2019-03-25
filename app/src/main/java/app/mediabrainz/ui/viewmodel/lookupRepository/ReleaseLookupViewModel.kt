package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Release
import app.mediabrainz.domain.repository.lookupRepository.ReleaseLookupRepository


class ReleaseLookupViewModel : BaseLookupViewModel<Release>() {

    private val repository = ReleaseLookupRepository()

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