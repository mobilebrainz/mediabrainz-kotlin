package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.ISWC
import app.mediabrainz.domain.repository.lookupRepository.ISWCLookupRepository


class ISWCLookupViewModel : BaseLookupViewModel<ISWC>() {

    private val repository = ISWCLookupRepository()

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