package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Disc
import app.mediabrainz.domain.repository.lookupRepository.DiscidLookupRepository


class DiscLookupViewModel(val repo: DiscidLookupRepository = DiscidLookupRepository()) :
    BaseLookupViewModel<Disc>(repo) {

    override fun lookup() {
        repo.lookup(result, mbid)
    }

}