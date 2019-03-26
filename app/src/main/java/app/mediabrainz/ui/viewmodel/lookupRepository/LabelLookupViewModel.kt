package app.mediabrainz.ui.viewmodel.lookupRepository

import app.mediabrainz.domain.model.Label
import app.mediabrainz.domain.repository.lookupRepository.LabelLookupRepository


class LabelLookupViewModel(val repo: LabelLookupRepository = LabelLookupRepository()) :
    BaseLookupViewModel<Label>(repo) {

    override fun lookup() {
        repo.lookup(result, mbid)
    }

}