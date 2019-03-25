package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Collection
import app.mediabrainz.domain.repository.lookupRepository.CollectionLookupRepository


class CollectionLookupViewModel : BaseLookupViewModel<Collection>() {

    private val repository = CollectionLookupRepository()

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