package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Url
import app.mediabrainz.domain.repository.lookupRepository.UrlLookupRepository


class UrlLookupViewModel : BaseLookupViewModel<Url>() {

    private val repository = UrlLookupRepository()

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