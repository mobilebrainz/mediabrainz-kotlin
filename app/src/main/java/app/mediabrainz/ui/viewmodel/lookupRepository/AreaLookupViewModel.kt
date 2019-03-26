package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Area
import app.mediabrainz.domain.repository.lookupRepository.AreaLookupRepository


class AreaLookupViewModel(val repo: AreaLookupRepository = AreaLookupRepository()) :
    BaseLookupViewModel<Area>(repo) {

    override fun lookup() {
        repo.lookup(result, mbid)
    }

}