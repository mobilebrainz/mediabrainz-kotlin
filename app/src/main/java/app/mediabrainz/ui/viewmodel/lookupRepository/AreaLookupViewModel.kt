package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Area
import app.mediabrainz.domain.repository.lookupRepository.AreaLookupRepository


class AreaLookupViewModel : BaseLookupViewModel<Area>() {

    private val repository = AreaLookupRepository()

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