package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Work
import app.mediabrainz.domain.repository.lookupRepository.WorkLookupRepository


class WorkLookupViewModel : BaseLookupViewModel<Work>() {

    private val repository = WorkLookupRepository()

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