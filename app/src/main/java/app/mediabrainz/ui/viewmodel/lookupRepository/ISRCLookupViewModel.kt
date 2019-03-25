package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.ISRC
import app.mediabrainz.domain.repository.lookupRepository.ISRCLookupRepository


class ISRCLookupViewModel : BaseLookupViewModel<ISRC>() {

    private val repository = ISRCLookupRepository()

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