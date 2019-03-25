package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.ReleaseGroup
import app.mediabrainz.domain.repository.lookupRepository.ReleaseGroupLookupRepository


class ReleaseGroupLookupViewModel : BaseLookupViewModel<ReleaseGroup>() {

    private val repository = ReleaseGroupLookupRepository()

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