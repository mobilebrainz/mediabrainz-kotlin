package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Collection
import app.mediabrainz.domain.repository.lookupRepository.CollectionLookupRepository


class CollectionLookupViewModel(val repo: CollectionLookupRepository = CollectionLookupRepository()) :
    BaseLookupViewModel<Collection>(repo) {

    override fun lookup() {
        repo.lookup(result, mbid)
    }

}