package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Disc
import app.mediabrainz.domain.repository.lookupRepository.DiscidLookupRepository


class DiscLookupViewModel : BaseLookupViewModel<Disc>() {

    private val repository = DiscidLookupRepository()

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