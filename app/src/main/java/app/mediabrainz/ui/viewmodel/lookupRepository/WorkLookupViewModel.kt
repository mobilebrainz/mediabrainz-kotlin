package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Work
import app.mediabrainz.domain.repository.lookupRepository.WorkLookupRepository


class WorkLookupViewModel(val repo: WorkLookupRepository = WorkLookupRepository()) :
    BaseLookupViewModel<Work>(repo) {

    override fun lookup() {
        repo.lookup(result, mbid)
    }

}